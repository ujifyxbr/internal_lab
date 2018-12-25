package learn.epam.mlhh.controllers;

import learn.epam.mlhh.entity.Candidate;
import learn.epam.mlhh.repository.CandidateRepository;
import learn.epam.mlhh.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class Parser {

    @Autowired
    private CandidateService candidateService;

    class Url {
        public int status;
        public String url;

        Url(int status, String url) {
            this.status = status;
            this.url = url;
        }
    }

    class DataParserAvito{
        ArrayList<Url> urlCategoriesList = new ArrayList<Url>();

        ArrayList<Url> urlPageList = new ArrayList<Url>();

        ArrayList<Url> urlResumeList = new ArrayList<Url>();

        String status = "";

        String urlMain = "https://www.avito.ru/nizhegorodskaya_oblast/rezume";

        String cssQueryCategories = "ul[data-marker=\"category[c112]/subs\"] > li > div a";

        String cssQueryPagesCategory = ".pagination .pagination-pages a:last-child";

        String cssQueryRezume = "div.item.item_table h3 a";

        String cssQueryblockDesc = ".item-params_type-one-colon li";

        String cssQueryblockContact = ".seller-info-prop .seller-info-value";

        String cssQueryblockSalary = ".item-price-value-wrapper span[itemprop=\"price\"]";

        String cssQueryblockTitle = "span.title-info-title-text";

        String regexpDeveloper = "Сфера деятельности:\\s*(.*)";

        String regexpAge = "Возраст:\\s*(.*)";

        String regexpGender = "Пол:\\s*(.*)";

        String regexpExperience = "Стаж работы:\\s*(.*)";

        String regexpRegion = "Нижегородская область,\\s*(.*)";


    }

    @RequestMapping(value="/parse", method=RequestMethod.GET)
    public String Avito(Map<String, Object> model, HttpSession session) throws IOException {
        DataParserAvito avito;
        Candidate candidate = new Candidate();

        if (session.getAttribute("avito") != null) {
            avito = (DataParserAvito) session.getAttribute("avito");
        } else {
            avito = new DataParserAvito();
        }

        try {
            avito.status = "Выполнение 1 этап из 4";
            //этап 1: спарсить url категорий
            if (avito.urlCategoriesList.size() == 0) {
                System.out.println("этап 1");
                avito.urlCategoriesList = getUrlList(avito.urlMain, avito.cssQueryCategories);
                if (avito.urlCategoriesList.size() == 0) throw new Exception();
            }
            System.out.println(avito.urlCategoriesList.size());
            avito.status = "Выполнен 1 этапа из 4";

            //этап 2: спарсить список всех страниц в категориях
            for (Url urlCategories : avito.urlCategoriesList) {
                System.out.println("этап 2");
                if (urlCategories.status == 1) continue;
                String urlLastPage = getUrl(urlCategories.url, avito.cssQueryPagesCategory);
                for (String urlPage : getListPages(urlLastPage)) {
                    if (urlPage instanceof String) {
                        avito.urlPageList.add(new Url(0, urlPage));
                    }
                }
                urlCategories.status = 1;
            }
            avito.status = "Выполнены 2 этапа из 4";

            //этап 3: спарсить все url объявлений из списка всех страниц категорий
            for (Url urlPage : avito.urlPageList) {
                System.out.println("этап 3");
                if (urlPage.status == 1) continue;
                avito.urlResumeList.addAll(getUrlList(urlPage.url, avito.cssQueryRezume));
                urlPage.status = 1;
              }
            avito.status = "Выполнены 3 этапа из 4";

            //этап 4: спарсить данные о кандидате
            for (Url urlResume : avito.urlResumeList) {
                System.out.println("этап 4");
                if (urlResume.status == 1) continue;
                Document docPage = Jsoup.connect(urlResume.url).get();
                candidate = new Candidate();
                candidate.setName(urlResume.url);

                //блок справо от фото с описанием
                Elements blockRightPhoto = docPage.select(avito.cssQueryblockDesc);
                String StrRightPhoto = "";
                for (Element block : blockRightPhoto) {
                    StrRightPhoto += block.text() + "\n";

                }

                Pattern pattern = Pattern.compile(avito.regexpDeveloper);
                Matcher matcher = pattern.matcher(StrRightPhoto);
                if (matcher.find()) {
                    if ("".equals(matcher.group(1))) throw new Exception();
                    candidate.setDeveloper(matcher.group(1));
                }

                 pattern = Pattern.compile(avito.regexpAge);
                 matcher = pattern.matcher(StrRightPhoto);
                if (matcher.find()) {
                    candidate.setAge(Integer.parseInt(matcher.group(1)));
                }

                pattern = Pattern.compile(avito.regexpGender);
                matcher = pattern.matcher(StrRightPhoto);
                if (matcher.find()) {
                    candidate.setGender(matcher.group(1));
                }

                pattern = Pattern.compile(avito.regexpExperience);
                matcher = pattern.matcher(StrRightPhoto);
                if (matcher.find()) {
                    try {
                        candidate.setExperience(Integer.parseInt(matcher.group(1)));
                    } catch (Exception e){
                        candidate.setExperience(0);
                    }

                }

                //блок контакты
                Elements blockContacts = docPage.select(avito.cssQueryblockContact);
                String StrBlockContacts;
                for (Element block : blockContacts) {
                    StrBlockContacts = block.text();
                    pattern = Pattern.compile(avito.regexpRegion);
                    matcher = pattern.matcher(StrBlockContacts);
                    if (matcher.find()) {
                        candidate.setRegion(matcher.group(1));
                    }
                }

                //блок зарплата
                Elements blockSalary = docPage.select(avito.cssQueryblockSalary);
                String StrblockSalary;
                for (Element block : blockSalary) {
                    StrblockSalary = block.text();
                    candidate.setSalary(Double.parseDouble(StrblockSalary.replaceAll("\\s", "")));
                }

                //блок название
                Elements blockKeyword = docPage.select(avito.cssQueryblockTitle);
                String StrblockKeyword;
                for (Element block : blockKeyword) {
                    StrblockKeyword = block.text();
                    candidate.setKeyWord(StrblockKeyword);
                }
                candidateService.createCandidate(candidate);
                urlResume.status = 1;
            }
            avito.status = "Парсинг выполнен успешно";
        } catch (Exception e) {
            e.printStackTrace();
            avito.status = "Ошибка парсинга. " +avito.status;
        }

        session.setAttribute("avito", avito);
        model.put("status", avito.status);
        model.put("listCategory", avito.urlCategoriesList);
        model.put("listPage", avito.urlPageList);
        model.put("listUrl", avito.urlResumeList);
        model.put("lastCandidate", candidate);
        return "parse";
    }



    private Document getDocumentJsoup(String url) throws IOException {
        Document docPage = Jsoup.connect(url)
                .timeout(5000)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
                .userAgent("Chrome/19.0.1042.0 Safari/535.21")
                .get();
        return docPage;
    }

    public ArrayList<Url> getUrlList( String url, String cssQuery) throws IOException {
        ArrayList<Url> urlList = new ArrayList<Url>();
        Document doc = getDocumentJsoup(url);
        Elements elements = doc.select(cssQuery);
        for (Element element : elements) {
            urlList.add(new Url(0, element.absUrl("href")));
        }
        return urlList;
    }

    public String getUrl( String url, String cssQuery) throws IOException {
        String urlParse = "";
        Document doc = getDocumentJsoup(url);
        Elements elements = doc.select(cssQuery);
        for (Element element : elements) {
            urlParse = element.absUrl("href");
        }
        return urlParse;
    }

    private ArrayList<String> getListPages(String urlLastPage) {
        ArrayList<String> urlList = new ArrayList<String>();
        String pair[] = urlLastPage.split("=");
        int lp = Integer.parseInt(pair[1]);
        for (int i = 1; i <= lp; ++i) {
            urlList.add(pair[0] + "=" + i);
        }
        return urlList;
    }
}

