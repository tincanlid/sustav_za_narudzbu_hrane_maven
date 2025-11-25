package entities;

import entities.Exceptions.OrderStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Predstavlja record o zavrsenoj narudzbi, koji sadrzi usera, item ,booking i status.
 */
public record Record (Optional<User> user, Optional<Item> item, Optional<Booking> booking, OrderStatus status) implements Trackable {

    /**
     * Prati zapis o naruzdbi.
     *
     * Ispisuje ime usera i naziv itema.
     */
    @Override
    public void track(){
        System.out.println("Tracking order: " + user.map(User::getIme).orElse("Nepoznato") + ": " + item.map(Item::getNaziv).orElse("Nepozanto"));
    }

    /**
     * Vraca string reprezentaciju recorda.
     *
     * @return String s imenom usera, imenom itema, datumom bookinga i statusom.
     */
    @Override
    public String toString(){
        return "Record(user=" + user.map(User::getIme).orElse("Prazno") + ", item= " + item.map(Item::getNaziv).orElse("Prazno") + ", booking= " + booking.map(Booking::getDate).orElse("Prazno") + ", status= " + status + ")";
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
