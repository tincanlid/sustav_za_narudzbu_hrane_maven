package entities;

/**
 * Iznimka koja se baca kada je format datuma neispravan.
 */
public class InvalidDateException extends Exception {
    public InvalidDateException(String message) {
        super(message);
    }
}
