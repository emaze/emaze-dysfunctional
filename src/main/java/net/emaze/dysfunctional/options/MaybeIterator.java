package net.emaze.dysfunctional.options;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;

/**
 * Decorates an Iterator yielding Maybe.just(element) for every element in the
 * contained iterator and Maybe.nothing() beyond the nested iterator bounds
 *
 * @param <E> the iterator element type
 * @author rferranti
 */
public class MaybeIterator<E> extends ReadOnlyIterator<Maybe<E>> {

    private final Iterator<E> iterator;

    public MaybeIterator(Iterator<E> iterator) {
        dbc.precondition(iterator != null, "trying to create a MaybeIterator from a null iterator");
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    /**
     * calling next over the boundary of the contained iterator leads
     * Maybe.nothing indefinitely "no matter how many times you try, you can't
     * shoot the dog"
     *
     * @return
     */
    @Override
    public Maybe<E> next() {
        if (iterator.hasNext()) {
            return Maybe.just(iterator.next());
        }
        return Maybe.nothing();
    }
}
