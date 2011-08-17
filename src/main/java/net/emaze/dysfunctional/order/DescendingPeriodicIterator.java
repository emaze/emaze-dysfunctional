package net.emaze.dysfunctional.order;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

public class DescendingPeriodicIterator<T> implements Iterator<T> {

    private final PeriodicSequencingPolicy<T> sequencer;
    private T prev;

    public DescendingPeriodicIterator(PeriodicSequencingPolicy<T> sequencer, T start) {
        dbc.precondition(sequencer != null, "cannot create DescendingPeriodicIterator with a null sequencer");
        this.sequencer = sequencer;
        this.prev = start;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        final T result = prev;
        prev = sequencer.prev(prev);
        return result;
    }

    @Override
    public void remove() {
        //ignore
    }
}
