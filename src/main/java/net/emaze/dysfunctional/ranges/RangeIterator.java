package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.Optional;
import net.emaze.dysfunctional.order.Order;
import net.emaze.dysfunctional.order.SequencingPolicy;

/**
 * Yields values generated from a Sequencing<T> policy in a given
 * range [ begin, end )
 *
 * @param <T>
 * @author rferranti
 */
public class RangeIterator<T> implements Iterator<T> {

    private final SequencingPolicy<T> policy;
    private T current;
    private final Optional<T> end;
    private Comparator<Optional<T>> comparator;

    public RangeIterator(SequencingPolicy<T> policy, Comparator<Optional<T>> comparator, T begin, Optional<T> end) {
        dbc.precondition(policy != null, "trying to create a RangeIterator from a null policy");
        dbc.precondition(comparator != null, "trying to create a RangeIterator from a null comparator");
        dbc.precondition(begin != null, "trying to create a RangeIterator from a null start");
        dbc.precondition(end != null, "trying to create a RangeIterator from a null upTo");
        this.policy = policy;
        this.current = begin;
        this.end = end;
        this.comparator = comparator;
    }

    @Override
    public boolean hasNext() {
        return Order.of(comparator, Optional.of(current), end).isLt();
    }

    @Override
    public T next() {
        final T oldCurrent = current;
        current = policy.next(current).get();
        return oldCurrent;
    }

    /**
     * ignore semantics
     */
    @Override
    public void remove() {
    }
}
