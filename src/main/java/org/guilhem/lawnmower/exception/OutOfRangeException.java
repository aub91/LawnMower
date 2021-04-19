package org.guilhem.lawnmower.exception;

/**
 * Exception thrown when a value of instruction file is out of range.
 */
public class OutOfRangeException extends MowingInstructionReadingException {
    public OutOfRangeException() {
    }

    public OutOfRangeException(String message) {
        super(message);
    }

    public OutOfRangeException(Throwable cause) {
        super(cause);
    }

    public OutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
