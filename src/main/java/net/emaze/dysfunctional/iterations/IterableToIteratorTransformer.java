package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class IterableToIteratorTransformer<T,V extends Iterable> implements Delegate<Iterator<T>,V> {

    @Override
    public Iterator<T> perform(V iterable) {
        return iterable.iterator();
    }

}
