package com.testing.ghr.beans;

import com.testing.ghr.beans.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to represent a team, that is an array of People
 */
@AllArgsConstructor
public class Team {

    /**
     * The team's name
     * @param name The team's name. Nicks
     * @return The current person's name. San Francisco
     */
    @Getter
    private final String name;

    /**
     * The people that integrates the team
     * @param members The team members
     * @return The current team members
     */
    @Getter
    private List<Person> members = new ArrayList<>();

}
