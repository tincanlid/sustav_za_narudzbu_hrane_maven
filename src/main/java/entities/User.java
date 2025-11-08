package entities;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Predstavlja usere u sustavu.
 *
 * Klasa sadrzi osnovne informacije kao sto su ime, dob i email.
 */
public class User extends Person{
    private String email;

    /**
     * Stvaranje novog objekta usera.
     *
     * @param id    Jedinstveni identifikator usera
     * @param ime  Ime usera
     * @param dob   Dob usera
     * @param email Email adresa usera
     */

    public User(int id, String ime, int dob, String email){
        super(id, ime, dob);
        this.email = email;
    }


    /**
     * Vraca email usera.
     * @return email usera
     */
    public String getEmail(){
        return email;
    }

    /**
     * Vraca string reprezentaciju usera.
     *
     * @return String s imenom i godinama usera
     */
    @Override
    public String toString(){
        return "User: " + super.toString() + ", email =" + email;
    }


    /**
     * Dijeli usere na punoljetne i maloljetne.
     *
     * @param users Users lista
     * @return
     */
    public static Map<Boolean, List<User>> podjelaPoDobi(List<User> users) {
        return users.stream()
                .collect(Collectors.partitioningBy(u -> u.getDob() >= 18));
    }

    /**
     * Grupira usere po godinama.
     *
     * @param users Users lista
     * @return
     */
    public static Map<Integer, List<User>> grupirajPoDobi(List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(User::getDob));
    }
}
