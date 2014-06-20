package com.example.nullpointer.controllers;

import com.example.nullpointer.domain.Person;
import com.example.nullpointer.repositories.PersonRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.aspects.core.NodeBacked;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody String index() {

        Person alice = new Person();
        alice.setFirstName("Alice");
        alice.setLastName("Smith");
        personRepository.save(alice);

        Person bob = new Person();
        bob.setFirstName("Bob");
        bob.setLastName("Jones");
        bob.addFriend(alice);
        personRepository.save(bob);

        Person person = personRepository.findByFirstName("Alice");
        
        return "Hello " + person.getFirstName();
    }

}
