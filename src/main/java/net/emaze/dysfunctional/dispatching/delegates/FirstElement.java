package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * gives up after consuming the first element
 * @param <E>
 * @author rferranti
 */
public class FirstElement<E> implements Delegate<E,Iterator<E>>{

    @Override
    public E perform(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        dbc.precondition(consumable.hasNext(), "no element to consume");
        return consumable.next();
    }

}
