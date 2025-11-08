package entities;

import entities.Exceptions.OrderStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Predstavlja record o zavrsenoj narudzbi, koji sadrzi usera, item ,booking i status.
 */
public record Record (User user, Item item, Booking booking, OrderStatus status) implements Trackable {

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
     * @return String s imenom usera, imenom itema, datumom bookinga i statusom.
     */
    @Override
    public String toString(){
        return "Record(user=" + user.getIme() + ", item= " + item.getNaziv() + ", booking= " + booking.getDate() + ", status= " + status + ")";
    }

    /**
     *
     * @param records
     * @return
     */
    public static Map<OrderStatus, List<Record>> grupirajPoStatusu(List<Record> records) {
        return records.stream()
                .collect(Collectors.groupingBy(Record::status));
    }
}
