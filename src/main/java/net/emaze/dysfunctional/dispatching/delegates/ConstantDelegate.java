package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;

/**
 * A delegate constantly returning the same value. Same as:
 * <code>Dispatching.ignore(new ConstantProvider<T>(value));</code>
 *
 * @author rferranti
 * @param <T> the delegate parameter type
 * @param <R> the delegate return type
 */
public class ConstantDelegate<T, R> implements Function<T, R> {

    private final R constant;

    public ConstantDelegate(R constant) {
        this.constant = constant;
    }

    @Override
    public R apply(T t) {
        return constant;
    }
}
