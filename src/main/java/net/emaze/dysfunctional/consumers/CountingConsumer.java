package net.emaze.dysfunctional.consumers;

import java.util.Iterator;

/**
 *
 * @param <T>
 * @param <E> 
 * @author rferranti
 */
public class CountingConsumer<T extends Number, E> implements Consumer<T,Iterator<E>>{

    @Override
    public T consume(Iterator<E> iterator) {
        Long count = 0l;
        while(iterator.hasNext()){
            ++count;
        }
        return (T) count;
    }

    
}
