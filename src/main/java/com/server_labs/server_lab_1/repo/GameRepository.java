package com.server_labs.server_lab_1.repo;

import com.server_labs.server_lab_1.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

}
