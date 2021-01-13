package com.testing.ghr.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Constructor that allows to get an instance of a Person with the name and the people related with
 * @param name The person's name
 * @param connections The a collection of persons associated to de person
 */
@AllArgsConstructor
/**
 * Constructor that allows to get an instance of a Person with just the name
 * @param name The person's name
 */
@RequiredArgsConstructor
/**
 * The person object is used to represent a Person and the persons related with
 */
public class Person {

    /**
     * Persons name
     */
    @Getter
    private final String name;

    /**
     * Persons connections
     */
    @Getter
    @Setter
    private List<Person> connections = null;

    @Override
    public String toString() {
        return this.name;
    }
}
