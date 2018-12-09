package learn.epam.mlhh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {
    @GetMapping("/searchdb")
    public String home(){
        return "searchdb";
    }

}
