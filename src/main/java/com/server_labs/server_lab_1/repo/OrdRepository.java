package com.server_labs.server_lab_1.repo;

import com.server_labs.server_lab_1.models.Ord;
import com.server_labs.server_lab_1.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdRepository extends CrudRepository<Ord, Integer> {
    Ord findByUserOrderByIdDesc(User user);
}
