package net.emaze.dysfunctional.consumers;

import java.util.Collection;
import java.util.Iterator;
import net.emaze.dysfunctional.collections.CollectionFactory;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * consumes every element from the consumable
 * @param <R>
 * @param <E>
 * @author rferranti
 */
public class EagerConsumer<R extends Collection<E>, E> implements Consumer<R, Iterator<E>> {

    private final CollectionFactory<R, E> factory;

    public EagerConsumer(CollectionFactory<R, E> factory) {
        dbc.precondition(factory != null, "collection factory cannot be null");
        this.factory = factory;
    }

    @Override
    public R consume(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        final R out = factory.create();
        while (consumable.hasNext()) {
            out.add(consumable.next());
        }
        return out;
    }
}
