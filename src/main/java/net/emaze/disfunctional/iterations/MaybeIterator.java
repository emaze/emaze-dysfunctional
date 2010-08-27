package net.emaze.disfunctional.iterations;

import java.util.Iterator;
import net.emaze.disfunctional.Maybe;

/**
 *
 * @author rferranti
 */
public class MaybeIterator<E> implements Iterator<Maybe<E>> {

    private final Iterator<E> iterator;

    public MaybeIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    /**
     * calling next over the boundary of the contained iterator leads Maybe.nothing indefinitely
     * "no matter how many times you try, you can't shoot the dog"
     * @return
     */
    public Maybe<E> next() {
        if(iterator.hasNext()){
            return Maybe.just(iterator.next());
        }
        return Maybe.nothing();
    }

    public void remove() {
        iterator.remove();
    }
}
