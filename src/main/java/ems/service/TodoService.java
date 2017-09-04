package ems.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import ems.model.Todo;

@Service
class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "in28minutes", "Learn Spring MVC", new Date(), false));
        todos.add(new Todo(2, "in28minutes", "Learn Strut", new Date(), false));
        todos.add(new Todo(3, "in28minutes", "Learn Hibernate", new Date(), false));
    }

    public List<Todo> retrieveTodos(String user) {
        List<Todo> resultList = new ArrayList<Todo>();
        if (user != null) {
            for (Todo todo : todos) {
                if (user.equals(todo.getUser())) {
                    resultList.add(todo);
                }
            }
        }
        return resultList;
    }

    public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
        todos.add(new Todo(++todoCount, desc, name, targetDate, isDone));
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}
