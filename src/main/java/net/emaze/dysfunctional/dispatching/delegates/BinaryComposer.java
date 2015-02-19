package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Composes an unary delegate with a binary delegate.
 *
 * @param <R> unary return type
 * @param <T1> unary parameter type and binary return type
 * @param <T2> the first binary parameter type
 * @param <T3> the second binary parameter type
 * @param unary the unary delegate to be composed
 * @param binary the binary delegate to be composed
 */
public class BinaryComposer<R, T1, T2, T3> implements BinaryDelegate<R, T2, T3> {

    private final BinaryDelegate<T1, T2, T3> binary;
    private final Function<T1, R> unary;

    public BinaryComposer(Function<T1, R> unary, BinaryDelegate<T1, T2, T3> binary) {
        dbc.precondition(unary != null, "cannot compose a null unary delegate");
        dbc.precondition(binary != null, "cannot compose a null binary delegate");
        this.unary = unary;
        this.binary = binary;
    }

    @Override
    public R perform(T2 first, T3 second) {
        return unary.apply(binary.perform(first, second));
    }
}
