package ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import ems.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {
    @Autowired
    private LoginService mLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleUserLogin(ModelMap model, @RequestParam String name, @RequestParam String password) {
        if (!mLoginService.validateUser(name, password)) {
            model.put("errorMessege", "Invalid Credentials");
            return "login";
        }
        model.put("name", name);
        return "welcome";
    }
}
