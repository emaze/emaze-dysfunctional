package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A proxy that returns the value provided by the given supplier, evaluated 
 * only once.
 *
 * @param <T> the supplier parameter type
 */
public class MemoizingSupplier<T> implements Supplier<T> {

    private final Supplier<T> supplier;
    private T value;
    private boolean isNotEvaluated = true;

    public MemoizingSupplier(Supplier<T> supplier) {
        dbc.precondition(supplier != null, "Cannot create an only once supplier with a null supplier");
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (isNotEvaluated) {
            value = supplier.get();
            isNotEvaluated = false;
        }
        return value;
    }
}
