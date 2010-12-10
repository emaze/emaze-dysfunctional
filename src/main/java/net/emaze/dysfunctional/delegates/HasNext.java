package net.emaze.dysfunctional.delegates;

import java.util.Iterator;
import net.emaze.dysfunctional.delegates.Predicate;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public class HasNext<T extends Iterator<?>> implements Predicate<T>{

    @Override
    public boolean test(T iterator) {
        return iterator.hasNext();
    }

}
