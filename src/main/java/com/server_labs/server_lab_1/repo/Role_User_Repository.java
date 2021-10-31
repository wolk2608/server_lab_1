package com.server_labs.server_lab_1.repo;

import com.server_labs.server_lab_1.models.Role_User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Role_User_Repository extends CrudRepository<Role_User, Integer> {

}
