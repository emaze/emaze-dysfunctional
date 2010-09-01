package net.emaze.dysfunctional.consumers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * consumes every element from the consumable
 * @author rferranti
 */
public class EagerConsumer<E> implements Consumer<List<E>,Iterator<E>>{
    
    public List<E> consume(Iterator<E> consumable){
        final List<E> out = new ArrayList<E>();
        while(consumable.hasNext()){
            out.add(consumable.next());
        }
        return out;
    }

}
