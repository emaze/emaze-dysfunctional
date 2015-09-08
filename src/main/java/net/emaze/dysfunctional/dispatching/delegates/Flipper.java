package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.BiFunction;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a binary function to another binary function by swapping formal
 * parameters.
 *
 * == No dolphins were harmed during the making of this functor ==
 *
 * @param <T> former formal parameter type of the given function
 * @param <U> latter formal parameter type of the given function
 * @param <R> return type of the given binary function
 * @author rferranti
 */
public class Flipper<T, U, R> implements BiFunction<T, U, R> {

    private final BiFunction<U, T, R> function;

    public Flipper(BiFunction<U, T, R> function) {
        dbc.precondition(function != null, "cannot flip a null binary function");
        this.function = function;
    }

    /**
     * Performs on the nested function swapping former and latter formal
     * parameters.
     *
     * @param former the former formal parameter used as latter in the nested
     * function
     * @param latter the latter formal parameter used as former in the nested
     * function
     * @return the result of the function
     */
    @Override
    public R apply(T former, U latter) {
        return function.apply(latter, former);
    }
}
