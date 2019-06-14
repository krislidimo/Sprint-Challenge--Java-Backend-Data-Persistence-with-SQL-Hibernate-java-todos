package com.lambdaschool.javatodo.services;

import com.lambdaschool.javatodo.models.Todo;

public interface TodoService {
    Todo save(Todo todo);

    Todo saveToUser(Todo todo, Long id);

    Todo update(Todo todo, long id);
}
