package net.emaze.dysfunctional.delegates;

/**
 * yields true at the nth iteration (1-based)
 * @param <E> the element type parameter
 * @author rferranti
 */
public class Nth<E> implements Predicate<E> {

    private final long target;
    private long current = 0;

    public Nth(long target) {
        this.target = target;
    }

    @Override
    public boolean test(E element) {
        return target == ++current;
    }

}
