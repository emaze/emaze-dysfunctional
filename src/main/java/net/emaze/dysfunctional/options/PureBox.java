package net.emaze.dysfunctional.options;

import java.util.function.Function;

/**
 * Pointed.pure() implementation of the {@literal Box<T>} functor.
 *
 * @author rferranti
 * @param <T> the box value type
 */
public class PureBox<T> implements Function<T, Box<T>> {

    @Override
    public Box<T> apply(T value) {
        return Box.of(value);
    }
}
