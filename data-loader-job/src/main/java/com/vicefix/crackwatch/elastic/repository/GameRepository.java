package com.vicefix.crackwatch.elastic.repository;

import com.vicefix.crackwatch.elastic.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {

}