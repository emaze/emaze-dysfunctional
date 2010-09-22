package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.Maybe;
import net.emaze.dysfunctional.numbers.OverflowingInteger;

/**
 * squared: [1,2] [a,b,c] -> just(1),just(a),just(2),just(b),nothing,just(c)
 * @author rferranti
 */
public class PreciseMultiplexingIterator<T> implements Iterator<Maybe<T>> {

    private final List<Iterator<Maybe<T>>> iterators;
    private final OverflowingInteger currentIndex;

    public PreciseMultiplexingIterator(Iterator<T>... iterators) {
        this.iterators = Iterations.map(iterators, new MaybeIteratorTransformer<T>());
        this.currentIndex = new OverflowingInteger(this.iterators.size());
    }

    @Override
    public boolean hasNext() {
        return Iterations.any(iterators, new HasNext());
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
