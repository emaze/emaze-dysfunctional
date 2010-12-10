package net.emaze.dysfunctional.delegates;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * 
 * @param <T>
 * @param <V> 
 * @author rferranti
 */
public class IteratorPlucker<T,V extends Iterable<T>> implements Delegate<Iterator<T>,V> {

    @Override
    public Iterator<T> perform(V iterable) {
        dbc.precondition(iterable != null, "iterable cannot be null");
        return iterable.iterator();
    }

}
