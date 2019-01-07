package learn.epam.mlhh.controllers;

import learn.epam.mlhh.service.CandidateService;
import learn.epam.mlhh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class HelloController {
    private String message = "Internal Lab";
    private String messageAdmin = "User management";

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Map<String, Object> model) {
        model.put("message", this.message);
        return "index";
    }

    @RequestMapping(value="/table", method=RequestMethod.POST)
    public String Table(Map<String, Object> model) {
        model.put("messageTable" , "Table");
        model.put("candidats" , candidateService.findAll() );
        return "table";
    }

    @RequestMapping(value="/admin", method=RequestMethod.POST)
    public String Admin(Map<String, Object> model) {
        model.put("messageAdmin" , this.messageAdmin);
        model.put("user" , userService.findAll());
        return "admin";
    }

    @RequestMapping(value="/403")
    public String Error403() {
        return "403";
    }
}
