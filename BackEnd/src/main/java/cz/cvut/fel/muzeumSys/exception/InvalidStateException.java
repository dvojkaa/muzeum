package cz.cvut.fel.muzeumSys.exception;

public class InvalidStateException extends MyException {

    public InvalidStateException(String message) {
        super(message);
    }

    public InvalidStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
