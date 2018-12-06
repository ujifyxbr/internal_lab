package learn.epam.mlhh.controllers;

import learn.epam.mlhh.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class HelloController {
    private String message = "Form";

    @Autowired
    private CandidateService candidateService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Map<String, Object> model) {
        model.put("message", this.message);
        return "index";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String homePost(Map<String, Object> model) {
        model.put("message", "you have sent value method Post");
        return "post";
    }

    @RequestMapping(value="/table", method=RequestMethod.POST)
    public String Table(Map<String, Object> model) {
        model.put("messageTable" , "Table");
        model.put("candidats" , candidateService.findAll() );
        return "table";
    }

}
