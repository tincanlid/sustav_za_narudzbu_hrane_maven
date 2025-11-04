package entities;

/**
 * Sealed interface koji oznacava entitet koji moze biti pracen.
 * Moze ga implementirati samo klasa `Record`.
 */
public sealed interface Trackable permits Record {
    /**
     * Pracenje stanja objekta.
     * Ova metoda omogucuje pracenje objekta u sustavu.
     */
    void track();
}
