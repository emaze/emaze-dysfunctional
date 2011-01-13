package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Maybe;

/**
 * shortest
 * @param <T> 
 * @author rferranti
 */
public class DemultiplexingIterator<T> implements Iterator<List<T>> {

    private final Iterator<T> iterator;
    private final int channelsCount;
    private Maybe<List<T>> prefetched = Maybe.nothing();

    public DemultiplexingIterator(Iterator<T> iterator, int channelsCount) {
        dbc.precondition(iterator != null, "iterator cannot be null");
        dbc.precondition(channelsCount > 0, "channels count must be > 0");
        this.iterator = iterator;
        this.channelsCount = channelsCount;
    }

    @Override
    public boolean hasNext() {
        if(!prefetched.hasValue()){
            prefetched = Maybe.just(prefetch(iterator, channelsCount));
        }
        return prefetched.value().size() == channelsCount;
    }

    @Override
    public List<T> next() {
        if(prefetched.hasValue()){
            if(prefetched.value().size() != channelsCount){
                throw new NoSuchElementException("iterator is not squared");
            }
            final List<T> value = prefetched.value();
            prefetched = Maybe.nothing();
            return value;
        }
        final List<T> out = new ArrayList<T>(channelsCount);
        for (int i = 0; i != channelsCount; ++i) {
            out.add(iterator.next());
        }
        return out;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove from a DemultiplexingIterator.");
    }

    private static <T> List<T> prefetch(Iterator<T> iter, int size){
        final List<T> result = new ArrayList<T>(size);
        for(int counter = 0;counter != size && iter.hasNext();++counter){
            result.add(iter.next());

        }
        return result;
    }
}
