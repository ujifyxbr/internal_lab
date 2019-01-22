package learn.epam.mlhh.controllers;

import learn.epam.mlhh.entity.Users;
import learn.epam.mlhh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/admin", method= RequestMethod.POST)
    public String Admin(Map<String, Object> model) {
        model.put("user" , userService.findAll());
        return "admin";
    }

    @RequestMapping(value="/admin/user_lock")
    public @ResponseBody String UserLock(@RequestParam String id) {

        Users result = new Users();
        result.setUserId(Integer.valueOf(id));
        userService.userLock(result.getUserId());

        return "true";
    }

    @RequestMapping(value="/admin/user_unlock")
    public @ResponseBody String UserUnlock(@RequestParam String id) {

        Users result = new Users();
        result.setUserId(Integer.valueOf(id));
        userService.userUnlock(result.getUserId());

        return "false";
    }
}
