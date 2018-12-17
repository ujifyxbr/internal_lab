package learn.epam.mlhh.controllers;


import learn.epam.mlhh.entity.Candidate;
import learn.epam.mlhh.entity.Search;
import learn.epam.mlhh.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private CandidateService candidateService;


    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("search", new Search());
        return "search";
    }

    @PostMapping("/search")
    public String searchSubmit(Search search, Map<String, Object> model) {

        double dSearchMin = search.getSalaryMin().doubleValue();
        double dSearchMax = search.getSalaryMax().doubleValue();

        List<Candidate> forsearch = candidateService.findAll();

        Iterator<Candidate> i = forsearch.iterator();
        while (i.hasNext()) {
            Candidate c = i.next(); // must be called before you can call i.remove()

            if (
                    c.getAge() < search.getAgeMin() || c.getAge() > search.getAgeMax()
                    || !(c.getGender().equals(search.getGender()))
                    || !(c.getRegion().equals(search.getRegion()))
                    || c.getSalary().compareTo(dSearchMin) < 0 || c.getSalary().compareTo(dSearchMax) > 0
                    || !(c.getDeveloper().equals(search.getDeveloper()))
                    || c.getExperience() < search.getExperienceMin() || c.getExperience() > search.getExperienceMax()
                    || !(c.getKeyWord().equals(search.getKeywords()))) {
                i.remove();
            }
        }
        model.put("searchPut", forsearch);

        return "searchresult";
    }
}



