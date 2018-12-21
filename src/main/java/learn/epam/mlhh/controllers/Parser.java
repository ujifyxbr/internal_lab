package learn.epam.mlhh.controllers;

import learn.epam.mlhh.entity.Candidate;
import learn.epam.mlhh.repository.CandidateRepository;
import learn.epam.mlhh.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import learn.epam.mlhh.repository.CandidateRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Parser {

    @Autowired
    private CandidateService candidateService;

    @RequestMapping(value="/parse", method=RequestMethod.GET)
    public String Avito(Map<String, Object> model) throws IOException {
        Candidate candidate = new Candidate();




        //этап 1: спарсить все категории
        ArrayList<String> listCategory = new ArrayList<String>();
        Document docPage = Jsoup.connect("https://www.avito.ru/nizhegorodskaya_oblast/rezume").get();
        Elements newsHeadlinesPage = docPage.select("ul[data-marker=\"category[c112]/subs\"] > li > div a");
        for (Element HeadlinesPage : newsHeadlinesPage) {
            listCategory.add(HeadlinesPage.absUrl("href"));
        }
        model.put("listCategory" , listCategory);


        //этап 2 спарсить все url страниц в категории
        ArrayList<String> listPage = new ArrayList<String>();
        String urlLastPage = "";
        docPage = Jsoup.connect(listCategory.get(0)).get();
        newsHeadlinesPage = docPage.select(".pagination .pagination-pages a:last-child");
        for (Element HeadlinesPage : newsHeadlinesPage) {
            urlLastPage = HeadlinesPage.absUrl("href");
        }
        String pair[] = urlLastPage.split("=");
        int lp = Integer.parseInt(pair[1]);
        for (int i=1; i <= lp; ++i) {
            listPage.add(pair[0] + "=" + i);
        }
        model.put("listPage" , listPage);


        //этап 3 спарсить все url страниц в резюме со страниц
        ArrayList<String> listUrl = new ArrayList<String>();
        docPage = Jsoup.connect(listPage.get(0)).get();
        newsHeadlinesPage = docPage.select("div.item.item_table h3 a");
        for(Element HeadLinePage : newsHeadlinesPage){
            listUrl.add(HeadLinePage.absUrl("href"));
        }
        model.put("listUrl" , listUrl);


        //этап 4 спарсить данные о кандидате
            //создаем структуру для кандидата
            Map<String, String> descCandidate = new HashMap<String, String>();

        docPage = Jsoup.connect(listUrl.get(0)).get();

        descCandidate.put("name", listUrl.get(0));
        candidate.setName(listUrl.get(0));

        //блок справо от фото
        Elements blockRightPhoto = docPage.select(".item-params_type-one-colon");
        String StrRightPhoto;
        for(Element block : blockRightPhoto) {
            StrRightPhoto = block.text();

            Pattern pattern = Pattern.compile("Возраст:\\s*(\\d*)");
            Matcher matcher = pattern.matcher(StrRightPhoto);
            if(matcher.find()) {
                descCandidate.put("age", matcher.group(1));
                candidate.setAge(Integer.parseInt(matcher.group(1)));
            }

            pattern = Pattern.compile("Пол:\\s*([а-яА-Я])");
            matcher = pattern.matcher(StrRightPhoto);
            if(matcher.find()) {
                descCandidate.put("gender", matcher.group(1));
                candidate.setGender(matcher.group(1));
            }

            pattern = Pattern.compile("Стаж работы:\\s*(\\d*)");
            matcher = pattern.matcher(StrRightPhoto);
            if(matcher.find()) {
                descCandidate.put("experience", matcher.group(1));
                candidate.setExperience(Integer.parseInt(matcher.group(1)));
            }

            descCandidate.put("developer", block.child(0).child(0).ownText());
            candidate.setDeveloper(block.child(0).child(0).ownText());
        }

        //блок контакты
        Elements blockContacts = docPage.select(".seller-info-prop .seller-info-value");
        String StrBlockContacts;
        for(Element block : blockContacts) {
            StrBlockContacts = block.text();
            Pattern pattern = Pattern.compile("Нижегородская область,\\s*(.*)");
            Matcher matcher = pattern.matcher(StrBlockContacts);
            if(matcher.find()) {
                descCandidate.put("region", matcher.group(1));
                candidate.setRegion(matcher.group(1));
            }
        }

        //блок зарплата
        Elements blockSalary = docPage.select(".item-price-value-wrapper span[itemprop=\"price\"]");
        String StrblockSalary;
        for(Element block : blockSalary) {
            StrblockSalary = block.text();
            descCandidate.put("salary", StrblockSalary.replaceAll("\\s", ""));
            candidate.setSalary(Double.parseDouble(StrblockSalary.replaceAll("\\s", "")));
        }

        //блок название
        Elements blockKeyword = docPage.select("span.title-info-title-text");
        String StrblockKeyword;
        for(Element block : blockKeyword) {
            StrblockKeyword = block.text();
            descCandidate.put("keyword", StrblockKeyword);
            candidate.setKeyWord(StrblockKeyword);
        }
        model.put("descCandidate" , descCandidate);

        candidateService.createCandidate(candidate);
        return "parse";
        //http://qaru.site/questions/697775/handling-connection-errors-and-jsoup
    }

    @RequestMapping(value="/p", method=RequestMethod.GET)
    public void Avitosss(Map<String, Object> model) throws IOException {
        System.out.println("<H1>Hello, world!</H1>");
    }

}
