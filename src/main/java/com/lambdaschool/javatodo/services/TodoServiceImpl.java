package com.lambdaschool.javatodo.services;

import com.lambdaschool.javatodo.models.Todo;
import com.lambdaschool.javatodo.models.User;
import com.lambdaschool.javatodo.repository.ToDoRepository;
import com.lambdaschool.javatodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Todo save(Todo todo) {
        return toDoRepository.save(todo);
    }

    @Override
    public Todo saveToUser(Todo todo, Long id) {
        User user = userRepository.findByUserid(id);

        return toDoRepository.save(new Todo(todo.getDescription(),todo.getDatestarted(), user));
    }

    @Transactional
    @Override
    public Todo update(Todo todo, long id) {
        Todo currentTodo = toDoRepository.findByTodoid(id);

        if (currentTodo != null) {
            if (id == currentTodo.getTodoid()) {
                if (todo.getDescription() != null) {
                    currentTodo.setDescription(todo.getDescription());
                }
                if (todo.getDatestarted() != null) {
                    currentTodo.setDatestarted(currentTodo.getDatestarted());
                }
                if (todo.getCompleted() != null) {
                    currentTodo.setCompleted(currentTodo.getCompleted());
                }
                if (todo.getUser() != null) {
                    currentTodo.setUser(currentTodo.getUser());
                }

                return toDoRepository.save(currentTodo);
            } else {
                throw new EntityNotFoundException(Long.toString(id) + " Not current user");
            }
        } else {
            throw new EntityNotFoundException(Long.toString(id));
        }

    }
}
