package net.emaze.dysfunctional.options;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Decorates an Iterator yielding Maybe.just(element) for every element in the
 * contained iterator and Maybe.nothing() beyond the nested iterator bounds
 * @param <E>
 * @author rferranti
 */
public class MaybeIterator<E> implements Iterator<Maybe<E>> {

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
     * calling next over the boundary of the contained iterator leads Maybe.nothing indefinitely
     * "no matter how many times you try, you can't shoot the dog"
     * @return
     */
    @Override
    public Maybe<E> next() {
        if(iterator.hasNext()){
            return Maybe.just(iterator.next());
        }
        return Maybe.nothing();
    }

    /**
     * removing from a MaybeIterator (at the moment) yields an UnsupportedOperationException
     * Maybe<TODO>: we could create a remove semantics
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("cannot remove from a MaybeIterator");
    }
}
