package com.testing.ghr.error;

/**
 * Exception used to identify that a team have no members so is not a team. A team of none is no team.
 * Actually, a team of less than one is no team...
 */
public class NoMembersException extends RuntimeException {
    private static final long serialVersionUID = 2610477518423283258L;

    public NoMembersException(String message) {
        super(message);
    }

}
