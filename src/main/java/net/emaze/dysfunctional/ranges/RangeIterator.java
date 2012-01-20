package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import net.emaze.dysfunctional.order.SequencingPolicy;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.Comparing;

/**
 * Yields values generated from a Sequencing<T> policy in a given range([start:upto])
 * @param <T> 
 * @author rferranti
 */
public class RangeIterator<T> implements Iterator<T> {

    private final SequencingPolicy<T> policy;
    private final Comparator<T> comparator;
    private T current;
    private final T upTo;

    public RangeIterator(SequencingPolicy<T> policy, Comparator<T> comparator, T start, T upTo) {
        dbc.precondition(policy != null, "trying to create a RangeIterator from a null policy");
        dbc.precondition(comparator != null, "trying to create a RangeIterator from a null comparator");
        dbc.precondition(start != null, "trying to create a RangeIterator from a null start");
        dbc.precondition(upTo != null, "trying to create a RangeIterator from a null upTo");
        this.policy = policy;
        this.comparator = comparator;
        this.current = start;
        this.upTo = upTo;
    }

    @Override
    public boolean hasNext() {
        return Comparing.lhsIsLesserThanEquals(current, upTo, comparator);
    }

    @Override
    public T next() {
        final T oldCurrent = current;
        current = policy.next(current);
        return oldCurrent;
    }

    /**
     * ignore semantics
     */
    @Override
    public void remove() {
        
    }
}
