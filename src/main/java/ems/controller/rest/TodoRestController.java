package ems.controller.rest;

import java.util.List;

import ems.model.Todo;
import ems.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoRestController {
    @Autowired
    private TodoService mTodoService;

    @RequestMapping( value = "/todo/", method = RequestMethod.GET )
    public List<Todo> listAllTodos() {
        return mTodoService.retrieveTodos( "in28minutes" );
    }

    public Todo retrieveTodo( @PathVariable( "id" ) int id ) {
        return mTodoService.retrieveTodo( id );
    }
}
