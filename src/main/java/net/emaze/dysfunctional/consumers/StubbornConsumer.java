package net.emaze.dysfunctional.consumers;

import java.util.Iterator;

/**
 * gives up only after consuming the last element (and returns it)
 * @author rferranti
 */
public class StubbornConsumer<E> implements Consumer<E,Iterator<E>>{

    @Override
    public E consume(Iterator<E> consumable) {
        if(!consumable.hasNext()){
            throw new IllegalStateException("no element to consume");
        }
        E value = consumable.next();
        while(consumable.hasNext()){
            value = consumable.next();
        }
        return value;
    }

}
