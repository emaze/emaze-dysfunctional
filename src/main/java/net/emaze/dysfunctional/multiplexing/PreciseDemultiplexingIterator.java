package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.options.Maybe;

/**
 * squared
 * @author rferranti
 */
public class PreciseDemultiplexingIterator<T> implements Iterator<List<Maybe<T>>> {

    private final Iterator<Maybe<T>> iterator;
    private final int channelsCount;

    public PreciseDemultiplexingIterator(Iterator<Maybe<T>> iterator, int channelsCount) {
        this.iterator = iterator;
        this.channelsCount = channelsCount;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public List<Maybe<T>> next() {
        final List<Maybe<T>> out = new ArrayList<Maybe<T>>(channelsCount);
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
