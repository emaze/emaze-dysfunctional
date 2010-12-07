package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * shortest
 * @param <T> 
 * @author rferranti
 */
public class DemultiplexingIterator<T> implements Iterator<List<T>> {

    private final Iterator<T> iterator;
    private final int channelsCount;

    public DemultiplexingIterator(Iterator<T> iterator, int channelsCount) {
        dbc.precondition(iterator != null, "iterator cannot be null");
        dbc.precondition(channelsCount > 0, "channels count must be > 0");
        this.iterator = iterator;
        this.channelsCount = channelsCount;
    }

    @Override
    public boolean hasNext() {
        //FIXME: wrong, should prefetch.
        return iterator.hasNext();
    }

    @Override
    public List<T> next() {
        final List<T> out = new ArrayList<T>(channelsCount);
        for (int i = 0; i != channelsCount; ++i) {
            out.add(iterator.next());
        }
        return out;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
