package net.emaze.dysfunctional.delegates;

/**
 * yields true at index `target` (0-based)
 * @param <E> the element type parameter
 * @author rferranti
 */
public class AtIndex<E> implements Predicate<E> {

    private final long target;
    private long current = 0;

    public AtIndex(long target) {
        this.target = target;
    }

    @Override
    public boolean test(E element) {
        return target == current++;
    }

}
