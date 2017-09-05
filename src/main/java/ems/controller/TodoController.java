package ems.controller;

import java.util.Date;

import javax.validation.Valid;

import ems.model.Todo;
import ems.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
    public String showAddTodoPage(ModelMap model) {
        model.addAttribute("todo", new Todo());
        return "todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todo";
        }
        mTodoService.addTodo((String) model.get("name"), todo.getDesc(), new Date(), false);
        model.clear();
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        mTodoService.deleteTodo(id);
        return "redirect:/list-todos";
    }
}
