package no.rogfk.sab.exception;

public class StudentAlreadyActivatedException extends RuntimeException {
    public StudentAlreadyActivatedException(String message) {
        super(message);
    }
}
