package ems.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import ems.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
    @Autowired
    private TodoService mTodoService;

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodosList(ModelMap model) {
        String user = (String) model.get("name");
        model.addAttribute("todos", mTodoService.retrieveTodos(user));
        return "list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showTodoPage() {
        return "todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @RequestParam String desc) {
        mTodoService.addTodo((String) model.get("name"), desc, new Date(), false);
        model.clear();
        return "redirect:/list-todos";
    }
}