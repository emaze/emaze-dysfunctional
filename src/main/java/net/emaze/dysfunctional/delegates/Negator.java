package net.emaze.dysfunctional.delegates;

/**
 * negates a predicate
 *  x => !predicate(x)
 * @author rferranti
 */
public class Negator<T> implements Predicate<T> {

    private final Predicate<T> predicate;

    public Negator(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(T element) {
        return !predicate.test(element);
    }
}
