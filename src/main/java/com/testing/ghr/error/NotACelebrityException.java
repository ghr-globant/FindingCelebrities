package com.testing.ghr.error;

/**
 * Exception used when the specified Person is not a celebrity.
 * A celebrity must be known by all the members of the group
 */
public class NotACelebrityException extends RuntimeException {

    public NotACelebrityException(String message) {
        super(message);
    }
}
