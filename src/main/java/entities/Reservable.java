package entities;

/**
 * Interface koji oznacava entitet koji moze biti rezerviran.
 * Klase koje implementiraju ovaj interface moraju definirati metodu za rezervaciju.
 */
public interface Reservable {
    /**
     * Rezervira objekt.
     * Ova metoda treba omoguciti korisnicima da izvrse rezervaciju.
     */
    void reserve();
}
