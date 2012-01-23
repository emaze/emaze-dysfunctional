package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.HasNext;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeIteratorTransformer;
import net.emaze.dysfunctional.order.IntegerSequencingPolicy;
import net.emaze.dysfunctional.order.PeriodicIterator;
import net.emaze.dysfunctional.order.PeriodicSequencingPolicy;
import net.emaze.dysfunctional.reductions.Any;

/**
 * squared: [1,2] [a,b,c] -> just(1),just(a),just(2),just(b),nothing,just(c)
 *
 * @param <E>
 * @author rferranti
 */
public class PreciseMultiplexingIterator<E> extends ReadOnlyIterator<Maybe<E>> {

    private final List<Iterator<Maybe<E>>> iterators = new ArrayList<Iterator<Maybe<E>>>();
    private final PeriodicIterator<Integer> indexSelector;

    public <T extends Iterator<E>> PreciseMultiplexingIterator(Iterator<T> iterators) {
        dbc.precondition(iterators != null, "trying to create a PreciseMultiplexingIterator from a null iterator of iterators");
        final Iterator<Iterator<Maybe<E>>> transformed = new TransformingIterator<Iterator<Maybe<E>>, T>(iterators, new MaybeIteratorTransformer<T, E>());
        while(transformed.hasNext()){
            this.iterators.add(transformed.next());
        }
        final PeriodicSequencingPolicy<Integer> period = new PeriodicSequencingPolicy<Integer>(new IntegerSequencingPolicy(), 0, this.iterators.size() - 1);
        this.indexSelector = new PeriodicIterator<Integer>(period, 0);
    }

    @Override
    public boolean hasNext() {
        return new Any<Iterator<Maybe<E>>>(new HasNext<Iterator<Maybe<E>>>()).accept(iterators.iterator());
    }

    @Override
    public Maybe<E> next() {
        return iterators.get(indexSelector.next()).next();
    }
}
