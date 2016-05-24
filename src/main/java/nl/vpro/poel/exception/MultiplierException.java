package nl.vpro.poel.exception;

/**
 * Exception thrown when a user tries to use more multipliers than allowed.
 */
public class MultiplierException extends Exception {

    public MultiplierException(String message) {
        super(message);
    }
}
