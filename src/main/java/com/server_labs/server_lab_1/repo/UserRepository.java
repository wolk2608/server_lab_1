package com.server_labs.server_lab_1.repo;

import com.server_labs.server_lab_1.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String user_login);
}
