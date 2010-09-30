package net.emaze.dysfunctional.delegates;

import java.util.Iterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeIterator;

/**
 * transforms an Iterator<T> in a MaybeIterator<T>
 * @author rferranti
 * @param <T>
 */
public class MaybeIteratorTransformer<T> implements Delegate<Iterator<Maybe<T>>, Iterator<T>> {

    @Override
    public Iterator<Maybe<T>> perform(Iterator<T> iterator) {
        return new MaybeIterator<T>(iterator);
    }
}
