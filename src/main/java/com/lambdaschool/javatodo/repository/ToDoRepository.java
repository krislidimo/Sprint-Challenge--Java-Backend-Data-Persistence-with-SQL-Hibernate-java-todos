package com.lambdaschool.javatodo.repository;

import com.lambdaschool.javatodo.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<Todo, Long> {
    Todo findByTodoid(Long id);
}
