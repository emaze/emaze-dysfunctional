package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * shortest
 * @author rferranti
 */
public class DemultiplexingIterator<T> implements Iterator<List<T>> {

    private final Iterator<T> iterator;
    private final int channelsCount;

    public DemultiplexingIterator(Iterator<T> iterator, int channelsCount) {
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
