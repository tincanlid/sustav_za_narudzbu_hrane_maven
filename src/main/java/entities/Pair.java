package entities;

public class Pair<T extends Number & Comparable<? super T>> {
    private final T first;
    private final T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T max() {
        return first.compareTo(second) >= 0 ? first : second;
    }

    public T min() {
        return first.compareTo(second) <= 0 ? first : second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
