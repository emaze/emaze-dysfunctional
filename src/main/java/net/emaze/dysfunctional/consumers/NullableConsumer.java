package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Predicate;

public class NullableConsumer<E> implements Consumer<E, Iterator<E>>{

    private final Predicate<E> predicate;

    public NullableConsumer(Predicate<E> predicate) {
        dbc.precondition(predicate != null, "predicate cannot be null");
        this.predicate = predicate;
    }

    @Override
    public E consume(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        while(consumable.hasNext()){
            final E elem = consumable.next();
            if(predicate.test(elem)){
                return elem;
            }
        }
        return null;
    }
}
