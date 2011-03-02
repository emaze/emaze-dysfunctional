package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Maybe;

/**
 * squared
 * @param <T> 
 * @author rferranti
 */
public class PreciseDemultiplexingIterator<T> implements Iterator<List<Maybe<T>>> {

    private final Iterator<Maybe<T>> iterator;
    private final int channelSize;

    public PreciseDemultiplexingIterator(int channelSize, Iterator<Maybe<T>> iterator) {
        dbc.precondition(channelSize > 0, "cannot build a PreciseDemultiplexingIterator with channelsCount < 1");
        dbc.precondition(iterator != null, "cannot build a PreciseDemultiplexingIterator with a null iterator");
        this.iterator = iterator;
        this.channelSize = channelSize;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public List<Maybe<T>> next() {
        final List<Maybe<T>> out = new ArrayList<Maybe<T>>(channelSize);
        for (int i = 0; i != channelSize; ++i) {
            out.add(iterator.next());
        }
        return out;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove from a PreciseDemultiplexingIterator.");
    }
}
