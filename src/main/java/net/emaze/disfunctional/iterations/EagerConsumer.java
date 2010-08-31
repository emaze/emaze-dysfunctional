package net.emaze.disfunctional.iterations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author rferranti
 */
public class EagerConsumer<E> {
    
    public List<E> consume(Iterator<E> consumable){
        final List<E> out = new ArrayList<E>();
        while(consumable.hasNext()){
            out.add(consumable.next());
        }
        return out;
    }
}
