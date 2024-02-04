package com.codeshow.tddwebflux.repository;


import com.codeshow.tddwebflux.model.Stack;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StackRepository extends ReactiveMongoRepository<Stack, String> {
}
