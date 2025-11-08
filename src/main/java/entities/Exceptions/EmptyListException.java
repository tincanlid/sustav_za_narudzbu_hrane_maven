package entities.Exceptions;

/**
 * Iznimka koja se baca kada se ocekuje da lista ili niz sadrzi elemente, ali je prazna.
 */
public class EmptyListException extends RuntimeException {
    public EmptyListException(String message) {
        super(message);
    }
}
