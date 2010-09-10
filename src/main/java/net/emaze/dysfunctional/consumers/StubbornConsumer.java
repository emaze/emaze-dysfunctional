package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * gives up only after consuming the last element (and returns it)
 * @author rferranti
 */
public class StubbornConsumer<E> implements Consumer<E,Iterator<E>>{

    @Override
    public E consume(Iterator<E> consumable) {
        dbc.precondition(consumable!=null, "consuming a null iterator");
        dbc.precondition(consumable.hasNext(),"no element to consume");
        E value = consumable.next();
        while(consumable.hasNext()){
            value = consumable.next();
        }
        return value;
    }

}
