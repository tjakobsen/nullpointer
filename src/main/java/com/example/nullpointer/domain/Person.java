package com.example.nullpointer.domain;

import java.util.HashSet;
import java.util.Set;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Person {

    @GraphId
    private Long Id;

    private String firstName;

    private String lastName;

    @RelatedTo(type = "FRIENDS_WITH", direction = Direction.BOTH)
    private Set<Person> friendsWith = new HashSet<Person>();

    public Person() {}
    
    public void addFriend(Person person) {
        friendsWith.add(person);
    }
    
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Person> getFriendsWith() {
        return friendsWith;
    }

    public void setFriendsWith(Set<Person> friendsWith) {
        this.friendsWith = friendsWith;
    }
}
