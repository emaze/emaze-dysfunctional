package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Predicate;

public class SingleMatchConsumer<E> implements Consumer<E, Iterator<E>>{

    private final Predicate<E> predicate;

    public SingleMatchConsumer(Predicate<E> predicate) {
        dbc.precondition(predicate != null, "predicate cannot be null");
        this.predicate = predicate;
    }

    @Override
    public E consume(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        E firstVal = searchFirst(consumable);
        assertNoOtherMatches(consumable);
        if (firstVal != null) {
            return firstVal;
        }
        throw new IllegalStateException("cannot find a matching element");
    }

    private void assertNoOtherMatches(Iterator<E> consumable) throws IllegalStateException {
        while (consumable.hasNext()) {
            final E elem = consumable.next();
            if (predicate.test(elem)) {
                throw new IllegalStateException("more than one match");
            }
        }
    }

    private E searchFirst(Iterator<E> consumable) {
        while (consumable.hasNext()) {
            final E elem = consumable.next();
            if (predicate.test(elem)) {
                return elem;
            }
        }
        return null;
    }
}
