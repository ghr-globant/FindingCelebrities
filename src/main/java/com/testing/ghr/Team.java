package com.testing.ghr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to represent a team, that is an array of People
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    /**
     * The team's name
     * @param name The team's name. Nicks
     * @return The current person's name. San Francisco
     */
    @Getter
    @Setter
    private String name = null;

    /**
     * The people that integrates the team
     * @param members The team members
     * @return The current team members
     */
    @Getter
    @Setter
    private List<Person> members = new ArrayList<>();

}
