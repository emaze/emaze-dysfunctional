package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.consumers.Consumer;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Counts the consumed elements 
 * @param <E> 
 * @author rferranti
 */
public class CountingReductor<E> implements Consumer<Long, Iterator<E>> {

    @Override
    public Long consume(Iterator<E> iterator) {
        dbc.precondition(iterator != null, "consuming a null iterator");
        long count = 0;
        for (; iterator.hasNext(); ++count) {
            iterator.next();
        }
        return count;
    }
}
