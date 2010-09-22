package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.delegates.Predicate;

/**
 *
 * @author rferranti
 */
public class HasNext<T extends Iterator> implements Predicate<T>{

    @Override
    public boolean test(T iterator) {
        return iterator.hasNext();
    }

}
