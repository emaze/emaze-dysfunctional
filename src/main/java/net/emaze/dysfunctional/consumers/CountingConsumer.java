package net.emaze.dysfunctional.consumers;

import java.util.Iterator;

/**
 * Counts the consumed elements 
 * @param <E> 
 * @author rferranti
 */
public class CountingConsumer<E> implements Consumer<Long, Iterator<E>> {

    @Override
    public Long consume(Iterator<E> iterator) {
        long count = 0;
        while (iterator.hasNext()) {
            ++count;
            iterator.next();
        }
        return count;
    }
}
