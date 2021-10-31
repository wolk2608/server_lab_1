package com.server_labs.server_lab_1.repo;

import com.server_labs.server_lab_1.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String role_name);
}
