package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * gives up after consuming the first element
 * @param <E>
 * @author rferranti
 */
public class GiveUpConsumer<E> implements Consumer<E,Iterator<E>>{

    @Override
    public E consume(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        dbc.precondition(consumable.hasNext(), "no element to consume");
        return consumable.next();
    }

}
