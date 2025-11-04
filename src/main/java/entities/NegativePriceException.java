package entities;

/**
 * Iznimka koja se baca kada se ocekuje da je cijena pozitivnog broja.
 */
public class NegativePriceException extends RuntimeException {
    public NegativePriceException(String message) {
        super(message);
    }
}
