package entities;

import entities.Exceptions.InvalidDateException;

/**
 * Predstavlja rezervaciju za usera.
 */
public class Booking implements Reservable{
    private int bookingId;
    private String date;

    /**
     * Stvaranje novog bookinga.
     *
     * @param BookingId Jedinstveni identifikator bookinga
     * @param date      Datum bookinga
     */
    public Booking(int BookingId, String date) throws InvalidDateException {
        if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
            throw new InvalidDateException("Datum mora biti u formatu DD-MM-YYYY");
        }
        this.bookingId = BookingId;
        this.date = date;
    }

    /**
     * Vraca id bookinga.
     *
     * @return id bookinga
     */
    public int getBookingId(){
        return bookingId;
    }

    /**
     * Vraca datum bookinga.
     *
     * @return datum bookinga
     */
    public String getDate(){
        return date;
    }

    /**
     * Ispisuje rezervaciju bookinga
     *
     */
    @Override
    public void reserve(){
        System.out.println("Booking #" + bookingId + " reserved za " + date);
    }

    /**
     * Vraca string reprezentaciju bookinga.
     *
     * @return String s ID-om bookinga i datumom
     */
    @Override
    public String toString(){
        return "Booking #" + bookingId + " (" + date + ")";
    }
}
