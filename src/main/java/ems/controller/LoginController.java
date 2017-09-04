package ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ems.service.LoginService;

@Controller
public class LoginController {
    private LoginService mLoginService = new LoginService();

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
