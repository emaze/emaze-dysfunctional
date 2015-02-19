package net.emaze.dysfunctional.dispatching.adapting;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Supplier;
import java.util.Optional;

/**
 * Adapts an {@code Iterator<T>} to a {@code Supplier<Optional<T>>}. This is a
 * stateful provider yielding Just elements of the source iterator, and Nothing
 * when the iterator is consumed.
 *
 * @author rferranti
 */
public class IteratingProvider<T> implements Supplier<Optional<T>> {

    private final Iterator<T> iterator;

    public IteratingProvider(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "cannot adapt a null iterator");
        this.iterator = iterator;
    }

    @Override
    public Optional<T> get() {
        return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.<T>empty();
    }
}
