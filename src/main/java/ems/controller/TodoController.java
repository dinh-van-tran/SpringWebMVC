package ems.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import ems.model.Todo;
import ems.service.TodoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {
    private static Logger mLog = Logger.getLogger(TodoController.class);

    @Autowired
    private TodoService mTodoService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor( Date.class, new CustomDateEditor( dateFormat, false ) );
    }

    @RequestMapping( value = "/list-todos", method = RequestMethod.GET )
    public String showTodosList( ModelMap model ) {
        mLog.debug("showTodosList begin");
        String user = getLoggedInUsername();
        model.addAttribute( "todos", mTodoService.retrieveTodos(user) );
        return "list-todos";
    }

    @RequestMapping( value = "/add-todo", method = RequestMethod.GET )
    public String showAddTodoPage( ModelMap model ) {
        model.addAttribute( "todo", new Todo() );
        return "todo";
    }

    @RequestMapping( value = "/add-todo", method = RequestMethod.POST )
    public String addTodo( ModelMap model, @Valid Todo todo, BindingResult result ) {
        if ( result.hasErrors() ) {
            return "todo";
        }
        mTodoService.addTodo( getLoggedInUsername(), todo.getDesc(), todo.getTargetDate(), false );
        model.clear();
        return "redirect:/list-todos";
    }

    @RequestMapping( value = "/delete-todo", method = RequestMethod.GET )
    public String deleteTodo( @RequestParam int id ) {
        mTodoService.deleteTodo( id );
        return "redirect:/list-todos";
    }

    @RequestMapping( value = "/update-todo", method = RequestMethod.GET )
    public String showUpdateTodoPage( ModelMap model, @RequestParam int id ) {
        model.addAttribute( "todo", mTodoService.retrieveTodo( id ) );
        return "todo";
    }

    @RequestMapping( value = "/update-todo", method = RequestMethod.POST )
    public String updateTodo( ModelMap model, @Valid Todo todo, BindingResult result ) {
        if ( result.hasErrors() ) {
            return "todo";
        }
        todo.setUser( getLoggedInUsername() );
        mTodoService.updateTodo( todo );
        model.clear();
        return "redirect:/list-todos";
    }

    private String getLoggedInUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        }
        return principal.toString();
    }
}
