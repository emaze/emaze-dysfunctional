package net.emaze.dysfunctional.searches;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * gives up only after consuming the last element (and returns it)
 * @param <E> 
 * @author rferranti
 */
public class LastElement<E> implements Delegate<E,Iterator<E>>{

    @Override
    public E perform(Iterator<E> consumable) {
        dbc.precondition(consumable!=null, "consuming a null iterator");
        dbc.precondition(consumable.hasNext(),"no element to consume");
        E value = consumable.next();
        while(consumable.hasNext()){
            value = consumable.next();
        }
        return value;
    }

}
