package entities;


/**
 * Iznimka koja se baca kada korisnik unese neispravan unos.
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
