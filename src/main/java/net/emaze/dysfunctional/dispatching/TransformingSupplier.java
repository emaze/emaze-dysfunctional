package net.emaze.dysfunctional.dispatching;

import java.util.function.Function;
import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Composes a function with a supplier (function ° supplier).
 *
 * @author rferranti
 * @param <R> the function result type
 * @param <T> the function parameter type
 */
public class TransformingSupplier<R, T> implements Supplier<R> {

    private final Function<T, R> transformer;
    private final Supplier<T> supplier;

    public TransformingSupplier(Function<T, R> transformer, Supplier<T> supplier) {
        dbc.precondition(transformer != null, "cannot compose supplier with a null transformer");
        dbc.precondition(supplier != null, "cannot compose transformer with a null supplier");
        this.transformer = transformer;
        this.supplier = supplier;
    }

    @Override
    public R get() {
        return transformer.apply(supplier.get());
    }
}
