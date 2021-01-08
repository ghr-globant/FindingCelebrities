package com.testing.ghr;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FindCelebrityTest {

    private FindCelebrityApp app;

    private Person celebrity = null;

    private Person john = null;
    private Person sam = null;
    private Person mark = null;
    private Person martha = null;

    @Before
    public void init() {

        app = new FindCelebrityApp();

        //The celebrity
        celebrity = new Person("Michel Jackson");

        //The people
        john = new Person("John Doe", Arrays.asList(new Person("Unknown")));
        sam = new Person("Sam Smith", Arrays.asList(john));
        mark = new Person("Mark Doe", Arrays.asList(sam, john));
        martha = new Person("Martha Wilkinson", Arrays.asList(sam, john, mark));

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

}
