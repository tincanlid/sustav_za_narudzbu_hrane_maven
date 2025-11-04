package entities;

/**
 * Predstavlja record o zavrsenoj narudzbi, koji sadrzi usera, item i booking.
 */
public record Record (User user, Item item, Booking booking) implements Trackable {

    /**
     * Prati zapis o naruzdbi.
     *
     * Ispisuje ime usera i naziv itema.
     */
    @Override
    public void track(){
        System.out.println("Tracking order: " + user.getIme() + ": " + item.getNaziv());
    }

    /**
     * Vraca string reprezentaciju recorda.
     *
     * @return String s imenom usera, imenom itema i datumom bookinga
     */
    @Override
    public String toString(){
        return "Record(user=" + user.getIme() + ", item= " + item.getNaziv() + ", booking= " + booking.getDate() + ")";
    }
}
