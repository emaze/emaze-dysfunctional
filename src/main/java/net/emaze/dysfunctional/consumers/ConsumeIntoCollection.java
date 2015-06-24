package net.emaze.dysfunctional.consumers;

import java.util.Collection;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Consumes every element from the consumable into the collection provided by
 * the supplier.
 *
 * @param <E> the collection element type parameter
 * @param <R> the collection type parameter
 * @author rferranti
 */
public class ConsumeIntoCollection<E, R extends Collection<E>> implements Function<Iterator<E>, R> {

    private final Supplier<R> supplier;

    public ConsumeIntoCollection(Supplier<R> supplier) {
        dbc.precondition(supplier != null, "collection supplier cannot be null");
        this.supplier = supplier;
    }

    @Override
    public R apply(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        final R out = supplier.get();
        while (consumable.hasNext()) {
            out.add(consumable.next());
        }
        return out;
    }
}
