package com.testing.ghr;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

@Log4j2
public class FindCelebrityApp {

    public FindCelebrityApp() {
        super();
    }

    public static void main(String[] args) {
        FindCelebrityApp app = new FindCelebrityApp();

        //The celebrity
        Person celebrity = new Person("Michel Jackson");

        //Create people involved with connections
        Person john = new Person("John Doe");
        Person sam = new Person("Sam Smith", Arrays.asList(john));
        Person mark = new Person("Mark Doe", Arrays.asList(sam, john));
        Person martha = new Person("Martha Wilkinson", Arrays.asList(sam, john, mark));
        john.setConnections(Arrays.asList(john));

        //Create teams
        List<Team> teams = Arrays.asList(
            new Team("Texas", Arrays.asList(john, martha, sam, celebrity)),
            new Team("Tampa", Arrays.asList(john, mark, sam, celebrity)),
            new Team("San Francisco", Arrays.asList(john, celebrity)),
            new Team("New York", Arrays.asList(celebrity)),
            new Team("Detroit", Arrays.asList(john, martha, sam)),
            new Team("Florida", null)
        );

        for(Team team : teams) {
            try {
                Person celeb = app.findTheCelebrity(team);
                if(celeb == null) {
                    out.println(String.format("The celebrity was not found at team \"%s\"", team.getName()));
                } else {
                    out.println(String.format("The celebrity at team \"%s\" is: %s", team.getName(), celeb));
                }
            } catch (NoMembersException e) {
                out.println(String.format("The team \"%s\" have no members", team.getName()));
            }
        }
    }

    /**
     * Method used to find a celebrity within a team
     * @param team The team to search into
     * @return The celebrity
     */
    public Person findTheCelebrity(Team team) throws NoMembersException {

        if(team.getMembers() == null || team.getMembers().size() <= 0) {
            throw new NoMembersException(String.format("The team %s has no members!", team.getName()));
        }

        return team.getMembers().stream().filter(p -> p.getConnections() == null).findFirst().orElse(null);
    }
}
