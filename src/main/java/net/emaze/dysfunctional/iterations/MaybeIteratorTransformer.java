package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.Maybe;
import net.emaze.dysfunctional.delegates.Delegate;

public class MaybeIteratorTransformer<T> implements Delegate<Iterator<Maybe<T>>, Iterator<T>> {

    @Override
    public Iterator<Maybe<T>> perform(Iterator<T> iterator) {
        return new MaybeIterator<T>(iterator);
    }
}
