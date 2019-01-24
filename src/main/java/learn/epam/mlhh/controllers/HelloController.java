package learn.epam.mlhh.controllers;

import learn.epam.mlhh.WebSecurityConfig;
import learn.epam.mlhh.entity.Candidate;
import learn.epam.mlhh.repository.CandidateAddRepo;
import learn.epam.mlhh.service.CandidateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@Controller
public class HelloController {
    private String message = "Internal Lab";
    private final 	static Logger logger = Logger.getLogger(WebSecurityConfig.class);

    @Autowired
    private CandidateService candidateService;
    @Autowired
    private CandidateAddRepo addRepo;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Map<String, Object> model) {
        model.put("message", this.message);
        return "index";
    }

    @RequestMapping(value="/table", method=RequestMethod.POST)
    public String Table(Map<String, Object> model) {
        model.put("messageTable" , "Table");
        model.put("candidats" , candidateService.findAll() );
        if(!(model.isEmpty())){
            logger.info("Candidates add sucsessfully");
        }
        else logger.error("Database error");
        return "table";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Map<String, Object> model) {

        Iterable<Candidate> candidates = addRepo.findAll();
        model.put("candidates", candidates);
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addData(@RequestParam String name, @RequestParam Integer age, @RequestParam String gender, @RequestParam String region,
                          @RequestParam BigDecimal salary, @RequestParam String developer, @RequestParam Integer experience, @RequestParam String keyword, Map<String, Object> model) {

        Candidate candidate = new Candidate(name, age, gender, region, salary, developer, experience, keyword);
        addRepo.save(candidate);
        Iterable<Candidate> candidates = addRepo.findAll();
        model.put("candidates", candidates);
        return "add";
    }

    @RequestMapping(value="/403")
    public String Error403() {
        return "403";
    }
}
