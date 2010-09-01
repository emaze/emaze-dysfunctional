package net.emaze.dysfunctional.consumers;

import java.util.Iterator;

/**
 * gives up after consuming the first element
 * @author rferranti
 */
public class GiveUpConsumer<E> implements Consumer<E,Iterator<E>>{

    public E consume(Iterator<E> consumable) {
        if(consumable.hasNext()){
            return consumable.next();
        }
        throw new IllegalStateException("no element to consume");
    }

}
