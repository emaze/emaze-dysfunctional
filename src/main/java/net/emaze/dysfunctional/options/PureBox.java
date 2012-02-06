package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Pointed.pure() implementation of the Box<T> functor.
 *
 * @author rferranti
 * @param <T> the box value type
 */
public class PureBox<T> implements Delegate<Box<T>, T> {

    @Override
    public Box<T> perform(T value) {
        return Box.of(value);
    }
}
