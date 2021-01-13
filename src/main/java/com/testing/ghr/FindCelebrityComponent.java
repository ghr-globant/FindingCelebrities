package com.testing.ghr;

import com.testing.ghr.beans.Person;
import com.testing.ghr.beans.Team;
import com.testing.ghr.error.NoMembersException;
import com.testing.ghr.error.NotACelebrityException;
import com.testing.ghr.error.TooManyCelebritiesException;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@NoArgsConstructor
public class FindCelebrityComponent {

    private static final String INVALID_CELEBRITY_MESSAGE = "The person found is not a celebrity. A celebrity must be known by all the members of the team. Person: %s";
    private static final String TOO_MANY_CELEBRITIES_MESSAGE = "The person found is not a celebrity. A celebrity must be known by all the members of the team. Person: %s";

    /**
     * Method used to find a celebrity within a team
     * This method will throw an exception if more than one celebrity is found
     * @param team The team to search into
     * @return The celebrity
     */
    public Person findTheCelebrity(Team team) throws NoMembersException {
        return findTheCelebrity(team,false);
    }

    /**
     * Method used to find a celebrity within a team
     * @param team The team to search into
     * @param getFirst Flag used to get the first celebrity found. If true and the team have more than one celebrity,
     *                 instead of throwing an error it will retrieve the first found
     * @return The celebrity
     */
    public Person findTheCelebrity(Team team, boolean getFirst) throws NoMembersException {
        log.debug("findTheCelebrity Team: {}", team);

        if(team.getMembers() == null || team.getMembers().size() <= 0) {
            throw new NoMembersException(String.format("The team %s has no members!", team.getName()));
        }

        log.debug("findTheCelebrity at team: {}", team.getName());

        List<Person> celebrities = team.getMembers().stream().filter(p -> p.getConnections() == null).collect(Collectors.toList());
        if(celebrities.size() <= 0) {
            return null;
        } else if(celebrities.size() == 1) {
            if(!isCelebrity(celebrities.get(0), team, celebrities.size())) {
                throw new NotACelebrityException(String.format(INVALID_CELEBRITY_MESSAGE, celebrities.get(0).getName()));
            } else return celebrities.get(0);
        } else {
            if(getFirst) {
                log.warn("More than one celebrity was found for team {}. Total number of celebrities found {}", team.getName(), celebrities.size());
                return celebrities.stream().filter(person -> isCelebrity(person, team, celebrities.size())).findFirst().orElse(null);
            } else {
                throw new TooManyCelebritiesException(String.format(TOO_MANY_CELEBRITIES_MESSAGE, celebrities.get(0).getName()));
            }
        }
    }

    /**
     * Method used to identify if a people within a team is a celebrity or not.
     * A celebrity must be known by all the members of the team that have connections.
     * A person is considered a celebrity when it does not have any connections
     * @param celebrity The celebrity to evaluate
     * @param team The team to be analyzed
     * @param celebrities The number of possible celebrities found in the team
     * @return True if the person is a celebrity, otherwise false
     */
    private boolean isCelebrity(Person celebrity, Team team, int celebrities) {
        long count = team.getMembers().stream().filter(
            p -> p.getConnections() != null && p.getConnections().contains(celebrity)
        ).count();

        return team.getMembers().stream().filter(
            p -> p.getConnections() != null && p.getConnections().contains(celebrity)
        ).count() == team.getMembers().size() - celebrities;
    }
}
