package learn.epam.mlhh.controllers;

import learn.epam.mlhh.domain.PutDB;
import learn.epam.mlhh.repos.PutBDRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
@Controller
public class SearchController {
    @Autowired
    private PutBDRepos putBDRepos;

    @GetMapping("/search")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "search";
    }
    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<PutDB> putDBS = putBDRepos.findAll();

        model.put("candidates", putDBS);
        return "main";
    }
    @PostMapping("/main")
    public String add (@RequestParam String name, @RequestParam Integer age, Map<String, Object> model){
        PutDB putDB = new PutDB(name,age);
        putBDRepos.save(putDB);


        return "main";
    }
}
