package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Maybe;
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
    private final Maybe<T> end;
    private Comparator<Maybe<T>> comparator;

    public RangeIterator(SequencingPolicy<T> policy, Comparator<Maybe<T>> comparator, T begin, Maybe<T> end) {
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
        return Order.of(comparator, Maybe.just(current), end).isLt();
    }

    @Override
    public T next() {
        final T oldCurrent = current;
        current = policy.next(current).value();
        return oldCurrent;
    }

    /**
     * ignore semantics
     */
    @Override
    public void remove() {
    }
}
