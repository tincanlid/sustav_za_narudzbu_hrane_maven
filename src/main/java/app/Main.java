package app;

import entities.*;
import entities.Exceptions.*;
import entities.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Gatherers;

/**
 * Sluzi za pokretanje programa!
 */
public class Main {
        private static final int BROJ_OBJEKATA = 2;
        private static final Logger log = LoggerFactory.getLogger(Main.class);

        /**
         * Glavna metoda gdje aplikacija zapocinje.
         * Rukuje unosom korisnika, artikla, rezervacija i narudzbi.
         *
         * @param args Argumenti komandne linije
         */
        static void main(String[] args) {

            log.info("Pokrenut program");
            Scanner scanner = new Scanner(System.in);



            List<User> users = new ArrayList<>();
            List<Item> items = new ArrayList<>();
            List<Admin> admins = new ArrayList<>();
            List<Booking> bookings = new ArrayList<>();
            List<Record> records = new ArrayList<>();


            System.out.println("Unos usera - ");
            log.info("Unos usera - ");


            try {

                for (int i = 0; i < BROJ_OBJEKATA; i++) {
                    log.debug("Unos usera broj " + (i + 1));

                    System.out.println("Ime: ");
                    log.info("Ime: ");
                    String ime = scanner.nextLine();
                    if (ime.isBlank()) {
                        log.warn("Prazan unos imena!");
                        throw new InvalidInputException("Ime ne smije biti prazno!");
                    }

                    System.out.println("Dob: ");
                    log.info("Dob: ");
                    if (!scanner.hasNextInt()) {
                        scanner.nextLine();
                        throw new InvalidInputException("Dob mora biti broj!");
                    }
                    int dob = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Email: ");
                    log.info("Email: ");
                    String email = scanner.nextLine();
                    if (!email.contains("@")) {
                        throw new InvalidInputException("Email mora sadržavati '@'!");
                    }

                    users.add(new User(i + 1, ime, dob, email));
                    log.debug("User dodan: " + users.get(i));
                    System.out.println();
                }

                System.out.println("Unos itema - ");
                log.info("Unos itema - : ");

                for (int i = 0; i < BROJ_OBJEKATA; i++) {
                    log.debug("Unos artikla broj " + (i + 1));

                    System.out.println("Naziv itema: ");
                    log.info("Naziv itema: ");
                    String naziv = scanner.nextLine();
                    if (naziv.isBlank()) {
                        throw new InvalidInputException("Naziv artikla ne smije biti prazan!");
                    }

                    System.out.println("Cijena: ");
                    log.info("Cijena: ");
                    if (!scanner.hasNextDouble()) {
                        scanner.nextLine();
                        throw new InvalidInputException("Cijena mora biti broj!");
                    }
                    double cijena = scanner.nextDouble();
                    scanner.nextLine();

                    items.add(new Item.Builder().setIme(naziv).setCijena(cijena).build());
                    log.debug("Item dodan: " + items.get(i));
                    System.out.println();
                }

                if (items.isEmpty()) {
                    throw new EmptyListException("Nema unesenih artikala — ne može se kreirati narudzba!");
                }

                System.out.println("Unos bookinga - ");
                log.info("Unos bookinga - ");

                for (int i = 0; i < BROJ_OBJEKATA; i++) {
                    System.out.println("Datum: ");
                    log.info("Datum: ");
                    String datum = scanner.nextLine();

                    bookings.add(new Booking(i + 1, datum));
                    log.debug("Booking stvoren: " + bookings.get(i));
                }

                System.out.println("Unos narudzbi - ");
                log.info("Unos narudzbi - ");

                for (int i = 0; i < BROJ_OBJEKATA; i++) {
                    records.add(new Record(
                            Optional.ofNullable(users.get(i)),
                            Optional.ofNullable(items.get(i)),
                            Optional.ofNullable(bookings.get(i)),
                            OrderStatus.NOVA
                    ));
                    log.debug("Record dodan: " + records.get(i));
                }

                // polje osoba sadrzi usere i admine
                Person[] osobe = new Person[BROJ_OBJEKATA * 2];
                for (int i = 0; i < BROJ_OBJEKATA; i++) {
                    osobe[i] = users.get(i);
                    osobe[i + BROJ_OBJEKATA] = admins.get(i);
                }

                System.out.println("Pretrazivanje usera - ");
                log.info("Pretrazivanje usera - ");
                log.trace("Pocetak unosa imena za pretragu");

                String imePrezime = scanner.nextLine();
                boolean imaUsera = false;

                for (int i = 0; i < BROJ_OBJEKATA; i++) {
                    if (users.get(i).getIme().equals(imePrezime)) {
                        System.out.println("User " + users.get(i).getIme() + " postoji");
                        log.info("User " + users.get(i).getIme() + " postoji");
                        imaUsera = true;
                    }
                }
                if (!imaUsera) {
                    System.out.println("User " + imePrezime + " ne postoji");
                    log.warn("User " + imePrezime + " ne postoji");
                }

                // sortiranje itema
                System.out.println("Sortirani item po cijeni (uzlazno): ");
                items.sort(Comparator.comparingDouble(Item::getCijena));
                items.forEach(item -> System.out.println(item.getNaziv() + " - " + item.getCijena() + " EUR"));

                System.out.println("Sortirani item po cijeni (silazno): ");
                items.sort(Comparator.comparingDouble(Item::getCijena).reversed());
                items.forEach(item -> System.out.println(item.getNaziv() + " - " + item.getCijena() + " EUR"));

                // sequenced collection (s Optional)
                Optional<User> prviUser = Optional.of(users.getFirst());
                Optional<User> zadnjIuser = Optional.of(users.getLast());

                System.out.println("Prvi i zadnji korisnik u listi:");
                System.out.println("Prvi: " + prviUser.map(User::getIme).orElse("Prazno"));
                System.out.println("Zadnji: " + zadnjIuser.map(User::getIme).orElse("Prazno"));

                // Stream Gatherers
                List<Double> zbrojeneCijene = items.stream()
                        .map(Item::getCijena)
                        .gather(java.util.stream.Gatherers.windowFixed(2))
                        .map(grupa -> grupa.stream().reduce(0.0, Double::sum))
                        .toList();

                System.out.println("Zbrojene cijene po grupama: " + zbrojeneCijene);

                // partitioningBy i groupingBy
                Map<Boolean, List<User>> podjela = User.podjelaPoDobi(users);
                Map<OrderStatus, List<Record>> grupirano = Record.grupirajPoStatusu(records);

                System.out.println("Najskuplji i najjeftiniji item - ");
                log.info("Najskuplji i najjeftiniji item - ");

                    Item najskuplji = items.stream().max(Comparator.comparingDouble(Item::getCijena)).orElseThrow();
                    Item najjeftiniji = items.stream().min(Comparator.comparingDouble(Item::getCijena)).orElseThrow();

                    System.out.println("Najskuplji item: " + najskuplji.getNaziv() + " " + najskuplji.getCijena() + " EUR");
                    System.out.println("Najjeftiniji item: " + najjeftiniji.getNaziv() + " " + najjeftiniji.getCijena() + " EUR");
                    log.info("Najskuplji item: " + najskuplji.getNaziv() + " " + najskuplji.getCijena() + " EUR");
                    log.info("Najjeftiniji item: " + najjeftiniji.getNaziv() + " " + najjeftiniji.getCijena() + " EUR");


                // LAB 5
                List<User> punoljetni = users.stream()
                        .filter(user -> user.getDob() >= 18)
                        .toList();
                System.out.println("Punoljetni korisnici: " + punoljetni);

                Optional<Double> ukupno = items.stream()
                        .map(Item::getCijena)
                        .reduce(Double::sum);
                System.out.println("Ukupna cijena svih itema: " + ukupno);

                List<Integer> ints = List.of(1, 2, 3);
                double s = KolUtils.sumNumbers(ints); // producer extends

                List<Object> objs = new ArrayList<>();
                KolUtils.addAllTo(objs, ints); // destination je super (consumer)

                Pair<Integer> pair = new Pair<>(4, 7);
                System.out.println("Pair max: " + pair.max());


            } catch (InvalidInputException | InvalidDateException e) {
                System.out.println("Greška kod unosa: " + e.getMessage());
                log.error("Greška kod unosa: " + e.getMessage());
            } catch (EmptyListException e){
                System.out.println("Upozorenje: " + e.getMessage());
                log.warn("Upozorenje: " + e.getMessage());
            } catch (Exception e){
                System.out.println("Neočekivana greška: " + e.getMessage());
                log.error("Neočekivana greška: " + e.getMessage());
            } finally {
                scanner.close();
                log.info("Zavrsetak programa");
            }

        }
}
