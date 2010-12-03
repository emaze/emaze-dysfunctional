package net.emaze.dysfunctional.delegates;

/**
 *
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
