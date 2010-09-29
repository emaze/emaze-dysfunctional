package net.emaze.dysfunctional.consumers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * consumes every element from the consumable
 * @param <E>
 * @author rferranti
 */
public class EagerConsumer<E> implements Consumer<List<E>,Iterator<E>>{
    
    @Override
    public List<E> consume(Iterator<E> consumable){
        dbc.precondition(consumable != null, "consuming a null iterator");
        final List<E> out = new ArrayList<E>();
        while(consumable.hasNext()){
            out.add(consumable.next());
        }
        return out;
    }

}
