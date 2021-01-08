package com.testing.ghr;

import java.util.List;

/**
 * The person object is used to represent a Person and the persons related with
 */
public class Person {

    private String name = null;
    private List<Person> connections = null;

    /**
     * Constructor that allows to get an instance of a Person with just the name
     * @param name The person's name
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * Constructor that allows to get an instance of a Person with the name and the people related with
     * @param name The person's name
     * @param connections The a collection of persons associated to de person
     */
    public Person(String name, List<Person> connections) {
        this.name = name;
        this.connections = connections;
    }

    /**
     * Method used to retrieve the person's name
     * @return The person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method used to set the person's name
     * @param name The person's new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method used to get the persons related to the current person
     * @return
     */
    public List<Person> getConnections() {
        return connections;
    }

    /**
     * Method used to set people's connections
     * @param connections The list of people associated
     */
    public void setConnections(List<Person> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
