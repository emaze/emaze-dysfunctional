package net.emaze.dysfunctional.filtering;

import java.util.function.Predicate;

/**
 * A stateful predicate yielding true when called the {@code nth - 1} time.
 *
 * @param <E> the element type parameter
 * @author rferranti
 */
public class AtIndex<E> implements Predicate<E> {

    private final long target;
    private long current = 0;

    public AtIndex(long target) {
        this.target = target;
    }

    /**
     * yields true at index target (0-based).
     *
     * @param element the ignored parameter
     * @return
     */
    @Override
    public boolean test(E element) {
        return target == current++;
    }
}
