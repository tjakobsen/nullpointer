package com.example.nullpointer.repositories;

import com.example.nullpointer.domain.Person;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PersonRepository extends GraphRepository<Person> {
    
    public Person findByFirstName(String firstName);
    
}
