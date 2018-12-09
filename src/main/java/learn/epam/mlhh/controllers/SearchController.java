package learn.epam.mlhh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class SearchController {

    private String message = "SEARCH";


    @GetMapping("/search")

    public String home (Map < String, Object > model){
        model.put("message", this.message);
        return "search";
    }

}
