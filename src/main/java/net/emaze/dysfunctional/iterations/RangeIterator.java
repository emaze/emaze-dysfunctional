package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.iterations.sequencing.SequencingPolicy;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
/**
 * Decorates an iterator enabling to consume only a range of its elements
 * @author rferranti
 */
public class RangeIterator<T> implements Iterator<T> {

    private final SequencingPolicy<T> policy;
    private T current;
    private final T upTo;

    public RangeIterator(SequencingPolicy<T> policy, T start, T upTo) {
        dbc.precondition(policy != null, "trying to create a RangeIterator from a null policy");
        dbc.precondition(start != null, "trying to create a RangeIterator from a null start");
        dbc.precondition(upTo != null, "trying to create a RangeIterator from a null upTo");
        this.policy = policy;
        this.current = start;
        this.upTo = upTo;
    }

    @Override
    public boolean hasNext() {
        return !current.equals(upTo);
    }

    @Override
    public T next() {
        current = policy.next(current);
        return current;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
