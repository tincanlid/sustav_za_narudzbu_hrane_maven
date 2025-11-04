package entities;


/**
 * Predstavlja admina usera u sustavu.
 * Nasljeduje klasu User i dodaje ulogu admina.
 */
public class Admin extends Person {
    private String rank;

    /**
     * Stvaranje novog objekta admina.
     *
     * @param id    Jedinstveni identifikator admina
     * @param ime  Ime admina
     * @param dob   Dob admina
     * @param rank  Uloga admina
     */
    public Admin(int id, String ime, int dob, String rank){
        super(id, ime ,dob);
        this.rank = rank;
    }

    /**
     * Vraca string reprezentaciju admina.
     *
     * @return String s imenom, godinama i ulogom admina
     */
    @Override
    public String toString(){
        return "Admin: " + super.toString() + ", rank=" + rank;
    }
}
