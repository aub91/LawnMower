package org.guilhem.lawnmower.exception;

/**
 * Exception thrown if an instruction file's line has wrong number of argument.
 */
public class WrongMowingInstructionFormat extends MowingInstructionReadingException {
    public WrongMowingInstructionFormat() {
    }

    public WrongMowingInstructionFormat(String message) {
        super(message);
    }

    public WrongMowingInstructionFormat(String message, Throwable cause) {
        super(message, cause);
    }
}
