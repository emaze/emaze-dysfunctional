package net.emaze.dysfunctional.multiplexing;

import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.HasNext;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.delegates.MaybeIteratorTransformer;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.numbers.CircularCounter;

/**
 * squared: [1,2] [a,b,c] -> just(1),just(a),just(2),just(b),nothing,just(c)
 * @param <T> 
 * @author rferranti
 */
public class PreciseMultiplexingIterator<T> implements Iterator<Maybe<T>> {

    private final List<Iterator<Maybe<T>>> iterators;
    private final CircularCounter currentIndex;

    public PreciseMultiplexingIterator(Iterator<T>... iterators) {
        dbc.precondition(iterators != null, "trying to create a ChainIterator from a null array of iterators");
        this.iterators = Iterations.map(iterators, new MaybeIteratorTransformer<T>());
        this.currentIndex = new CircularCounter(this.iterators.size());
    }

    @Override
    public boolean hasNext() {
        return Iterations.any(iterators, new HasNext<Iterator<Maybe<T>>>());
    }

    @Override
    public Maybe<T> next() {
        return iterators.get(currentIndex.getAndIncrement()).next();
    }

    @Override
    public void remove() {
        iterators.get(currentIndex.get()).remove();
    }
}
