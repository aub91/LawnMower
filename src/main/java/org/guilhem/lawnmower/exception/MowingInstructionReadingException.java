package org.guilhem.lawnmower.exception;

public class MowingInstructionReadingException extends Exception {
    public MowingInstructionReadingException() {
    }

    public MowingInstructionReadingException(String message) {
        super(message);
    }

    public MowingInstructionReadingException(Throwable cause) {
        super(cause);
    }

    public MowingInstructionReadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
