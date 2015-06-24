package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;

/**
 * A function constantly returning the same value. Same as:
 * <code>Dispatching.ignore(new ConstantSupplier<T>(value));</code>
 *
 * @author rferranti
 * @param <T> the function parameter type
 * @param <R> the function return type
 */
public class ConstantFunction<T, R> implements Function<T, R> {

    private final R constant;

    public ConstantFunction(R constant) {
        this.constant = constant;
    }

    @Override
    public R apply(T t) {
        return constant;
    }
}
