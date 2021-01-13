package com.testing.ghr.error;

/**
 * Exception used when more than one celebrity is found within a team.
 */
public class TooManyCelebritiesException extends RuntimeException {

    public TooManyCelebritiesException(String message) {
        super(message);
    }
}

