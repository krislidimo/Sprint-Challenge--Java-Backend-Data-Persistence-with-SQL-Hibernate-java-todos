package com.lambdaschool.javatodo.repository;

import com.lambdaschool.javatodo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);

    User findByUserid(Long id);
}
