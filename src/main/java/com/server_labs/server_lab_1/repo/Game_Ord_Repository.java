package com.server_labs.server_lab_1.repo;

import com.server_labs.server_lab_1.models.Game_Ord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Game_Ord_Repository extends CrudRepository<Game_Ord, Integer> {

}
