package com.testing.ghr;

import com.testing.ghr.beans.Person;
import com.testing.ghr.beans.Team;
import com.testing.ghr.error.NoMembersException;
import com.testing.ghr.error.NotACelebrityException;
import com.testing.ghr.error.TooManyCelebritiesException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * A FindCelebrityComponent test
 * @see com.testing.ghr.FindCelebrityComponent
 */
public class FindCelebrityTest {

    private FindCelebrityComponent app;

    private Person celebrity = null;

    private Person john = null;
    private Person sam = null;
    private Person mark = null;
    private Person martha = null;

    @Before
    public void init() {

        app = new FindCelebrityComponent();

        celebrity = new Person("Michel Jackson");

        john = new Person("John Doe", Arrays.asList(new Person("Unknown"), celebrity));
        sam = new Person("Sam Smith", Arrays.asList(john, celebrity));
        mark = new Person("Mark Doe", Arrays.asList(sam, john, celebrity));
        martha = new Person("Martha Wilkinson", Arrays.asList(sam, john, mark, celebrity));

    }

    @Test(expected = NoMembersException.class)
    public void testZeroTeam() {
        app.findTheCelebrity(new Team("Florida", null));
    }

    @Test
    public void testOneTeam() {
        Person celeb = app.findTheCelebrity(new Team("Pittsburgh", Arrays.asList(martha)));
        assertEquals(celeb, null);
    }

    @Test
    public void testOneCelebrityTeam() {
        Person celeb = app.findTheCelebrity(new Team("New York", Arrays.asList(celebrity)));
        assertEquals(celeb, celebrity);
    }

    @Test
    public void testFullTeam() {
        Person celeb = app.findTheCelebrity(new Team("Texas", Arrays.asList(john, martha, sam, celebrity)));
        assertEquals(celeb, celebrity);
    }

    @Test
    public void testNoCelebTeam() {
        Person celeb = app.findTheCelebrity(new Team("Detroit", Arrays.asList(john, martha, sam)));
        assertEquals(celeb, null);
    }

    @Test(expected = TooManyCelebritiesException.class)
    public void testMoreThanOneCelebTeam() {
        Person aCelebrity = new Person("John Lennon");
        Person celeb = app.findTheCelebrity(new Team("Oklahoma", Arrays.asList(john, martha, sam, celebrity, aCelebrity)));
        assertNull(celeb);
    }

    @Test
    public void testMoreThanOneCelebTeamJustWarn() {
        Person aCelebrity = new Person("John Lennon");
        Person celeb = app.findTheCelebrity(new Team("Oklahoma", Arrays.asList(john, martha, sam, celebrity, aCelebrity)), true);
        assertEquals(celeb, celebrity);
    }

    @Test(expected = NotACelebrityException.class)
    public void testNotACelebrityTeam() {
        Person newCelebrity = new Person("Bruce Springsteen");
        app.findTheCelebrity(new Team("Dallas", Arrays.asList(john, martha, sam, newCelebrity)));
    }

}
