package org.guilhem.lawnmower.exception;

/**
 * Exception thrown if an orientation is not a known one.
 */
public class UnknownOrientationException extends MowingInstructionReadingException {
    public UnknownOrientationException() {
    }

    public UnknownOrientationException(String message) {
        super(message);
    }
}
