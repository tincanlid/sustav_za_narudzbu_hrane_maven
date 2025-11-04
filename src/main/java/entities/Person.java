package entities;

/**
 * Apstraktna klasa koja predstavlja osnovne informacije o osobi.
 * Klasa sadrzi osnovne podatke o imenu, dobi i ID-u.
 */
public abstract class Person {
    protected int id;
    protected String ime;
    protected int dob;

    /**
     * Konstruktor za stvaranje novog objekta osobe.
     *
     * @param id    Jedinstveni identifikator osobe
     * @param ime  Ime osobe
     * @param dob   Dob osobe
     */
    public Person(int id, String ime, int dob){
        this.id = id;
        this.ime = ime;
        this.dob = dob;
    }

    /**
     * Vraca jedinstveni id osobe.
     *
     * @return ID osobe
     */
    public int getId() {
        return id;
    }

    /**
     * Vraca ime osobe.
     *
     * @return Ime osobe
     */
    public String getIme() {
        return ime;
    }

    /**
     * Vraca dob osobe.
     *
     * @return Dob osobe
     */
    public int getDob() {
        return dob;
    }

    /**
     * Vraca string reprezentaciju osobe.
     *
     * @return String s imenom i godinama osobe
     */
    @Override
    public String toString(){
        return ime + " (" + dob + " godina)";
    }
}
