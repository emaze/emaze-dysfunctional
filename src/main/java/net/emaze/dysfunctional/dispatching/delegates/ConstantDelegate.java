package net.emaze.dysfunctional.dispatching.delegates;

/**
 * Constantly returns a value. Same as:
 * <code>Dispatching.ignore(new ConstantProvider<T>(value));</code>
 *
 * @author rferranti
 * @param <R> the delegate return type
 * @param <T> the delegate parameter type
 */
public class ConstantDelegate<R, T> implements Delegate<R, T> {

    private final R constant;

    public ConstantDelegate(R constant) {
        this.constant = constant;
    }

    @Override
    public R perform(T t) {
        return constant;
    }
}
