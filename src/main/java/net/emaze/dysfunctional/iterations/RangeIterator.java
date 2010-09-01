package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.iterations.sequencing.SequencingPolicy;
import java.util.Iterator;

public class RangeIterator<T> implements Iterator<T> {

    private final SequencingPolicy<T> policy;
    private T current;
    private final T upTo;

    public RangeIterator(SequencingPolicy<T> policy, T start, T upTo) {
        this.policy = policy;
        this.current = start;
        this.upTo = upTo;
    }

    public boolean hasNext() {
        return !current.equals(upTo);
    }

    public T next() {
        current = policy.next(current);
        return current;
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
