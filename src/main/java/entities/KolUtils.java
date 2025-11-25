package entities;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility metode koje demonstriraju PECS, upper/lower bounded wildcards i multiple bounds.
 */
public final class KolUtils {

    private KolUtils() {}

    /**
     * PRODUCER: Upper bounded (PECS) - zbraja listu brojeva.
     * Moze proslijediti List<Integer>, List<Double>, ...
     */
    public static double sumNumbers(List<? extends Number> numbers) {
        return numbers.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    /**
     * CONSUMER: Lower bounded (PECS) - dodaje sve elemente iz source u destination.
     * destination moze biti List<Object>, List<Number> itd.
     */
    public static <T> void addAllTo(List<? super T> destination, List<? extends T> source) {
        for (T elem : source) {
            destination.add(elem);
        }
    }
}