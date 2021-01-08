package com.testing.ghr;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.System.out;

@Log4j2
public class FindCelebrityApp {

    public FindCelebrityApp() {
        super();
    }

    public static void main(String[] args) {
        log.info("Starting up FindCelebrityApp!");
        FindCelebrityApp app = new FindCelebrityApp();

        Person celebrity = new Person("Michel Jackson");

        log.debug("Celebrity set to: {} loading persons...", celebrity.getName());
        Person john = new Person("John Doe");
        Person sam = new Person("Sam Smith", Collections.singletonList(john));
        Person mark = new Person("Mark Doe", Arrays.asList(sam, john));
        Person martha = new Person("Martha Wilkinson", Arrays.asList(sam, john, mark));
        john.setConnections(Collections.singletonList(john));

        log.debug("Persons created, setting teams...");
        List<Team> teams = Arrays.asList(
            new Team("Texas", Arrays.asList(john, martha, sam, celebrity)),
            new Team("Tampa", Arrays.asList(john, mark, sam, celebrity)),
            new Team("San Francisco", Arrays.asList(john, celebrity)),
            new Team("New York", Collections.singletonList(celebrity)),
            new Team("Detroit", Arrays.asList(john, martha, sam)),
            new Team("Florida", null)
        );

        for(Team team : teams) {
            try {
                Person celeb = app.findTheCelebrity(team);
                if(celeb == null) {
                    out.printf("The celebrity was not found at team \"%s\"%n", team.getName());
                } else {
                    out.printf("The celebrity at team \"%s\" is: %s%n", team.getName(), celeb);
                }
            } catch (NoMembersException e) {
                out.printf("The team \"%s\" have no members%n", team.getName());
            } catch (Exception e) {
                log.error("Unknown error occurred getting the celebrity!", e);
            }
        }
    }

    /**
     * Method used to find a celebrity within a team
     * @param team The team to search into
     * @return The celebrity
     */
    public Person findTheCelebrity(Team team) throws NoMembersException {
        log.debug("findTheCelebrity Team: {}", team);

        if(team.getMembers() == null || team.getMembers().size() <= 0) {
            throw new NoMembersException(String.format("The team %s has no members!", team.getName()));
        }

        log.debug("findTheCelebrity at team: {}", team.getName());
        return team.getMembers().stream().filter(p -> p.getConnections() == null).findFirst().orElse(null);
    }
}
