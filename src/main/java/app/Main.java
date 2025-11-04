package app;

import entities.*;
import entities.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Sluzi za pokretanje programa!
 */
public class Main {
        private static final int BROJ_OBJEKATA = 2;
        private static Logger log = LoggerFactory.getLogger(Main.class);

        /**
         * Glavna metoda gdje aplikacija zapocinje.
         * Rukuje unosom korisnika, artikla, rezervacija i narudzbi.
         *
         * @param args Argumenti komandne linije
         */
        static void main(String[] args) {

            log.info("Pokrenut program");
            Scanner scanner = new Scanner(System.in);


            User[] users = new User[BROJ_OBJEKATA];
            Admin[] admins = new Admin[BROJ_OBJEKATA];
            Item[] items = new Item[BROJ_OBJEKATA];
            Booking[] bookings = new Booking[BROJ_OBJEKATA];
            Record[] records = new Record[BROJ_OBJEKATA];

            System.out.println("Unos usera - ");
            log.info("Unos usera - ");


            try {

                for (int i = 0; i < users.length; i++) {
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

                    users[i] = new User(i + 1, ime, dob, email);
                    log.debug("User dodan: " + users[i]);
                    System.out.println();
                }

                System.out.println("Unos itema - ");
                log.info("Unos itema - : ");

                for (int i = 0; i < items.length; i++) {
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

                    items[i] = new Item.Builder().setIme(naziv).setCijena(cijena).build();
                    log.debug("Item dodan: " + items[i]);
                    System.out.println();
                }

                if (items == null || items.length == 0 || items[0] == null) {
                    throw new EmptyListException("Nema unesenih artikala — ne može se kreirati narudzba!");
                }

                System.out.println("Unos bookinga - ");
                log.info("Unos bookinga - ");

                for (int i = 0; i < bookings.length; i++) {
                    System.out.println("Datum: ");
                    log.info("Datum: ");
                    String datum = scanner.nextLine();

                    bookings[i] = new Booking(i + 1, datum);
                    log.debug("Booking stvoren: " + bookings[i]);
                }

                System.out.println("Unos narudzbi - ");
                log.info("Unos narudzbi - ");

                for (int i = 0; i < records.length; i++) {
                    records[i] = new Record(users[i], items[i], bookings[i]);
                    log.debug("Record dodan: " + records[i]);
                }

                // polje osoba sadrzi usere i admine
                Person[] osobe = new Person[BROJ_OBJEKATA * 2];
                for (int i = 0; i < BROJ_OBJEKATA; i++) {
                    osobe[i] = users[i];
                    osobe[i + BROJ_OBJEKATA] = admins[i];
                }

                System.out.println("Pretrazivanje usera - ");
                log.info("Pretrazivanje usera - ");
                log.trace("Pocetak unosa imena za pretragu");

                String imePrezime = scanner.nextLine();
                boolean imaUsera = false;

                for (int i = 0; i < users.length; i++) {
                    if (users[i].getIme().equals(imePrezime)) {
                        System.out.println("User " + users[i].getIme() + " postoji");
                        log.info("User " + users[i].getIme() + " postoji");
                        imaUsera = true;
                    }
                }
                if (imaUsera == false) {
                    System.out.println("User " + imePrezime + " ne postoji");
                    log.warn("User " + imePrezime + " ne postoji");
                }

                System.out.println("Najskuplji i najjeftiniji item - ");
                log.info("Najskuplji i najjeftiniji item - ");

                if (items.length > 0) {
                    Item najskuplji = items[0];
                    Item najjeftiniji = items[0];

                    int i = 1;
                    do {
                        log.trace("Provjera itema " + (i+1) + ": " + items[i]);
                        if (items[i].getCijena() > najskuplji.getCijena()) {
                            najskuplji = items[i];
                        }
                        if (items[i].getCijena() < najjeftiniji.getCijena()) {
                            najjeftiniji = items[i];
                        }
                        i++;
                    } while (i < items.length);

                    System.out.println("Najskuplji item: " + najskuplji.getNaziv() + " " + najskuplji.getCijena() + " EUR");
                    System.out.println("Najjeftiniji item: " + najjeftiniji.getNaziv() + " " + najjeftiniji.getCijena() + " EUR");
                    log.info("Najskuplji item: " + najskuplji.getNaziv() + " " + najskuplji.getCijena() + " EUR");
                    log.info("Najjeftiniji item: " + najjeftiniji.getNaziv() + " " + najjeftiniji.getCijena() + " EUR");
                }
                else {
                    throw new EmptyListException("Nema itema za usporedbu!");
                }
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
            }

        }
}
