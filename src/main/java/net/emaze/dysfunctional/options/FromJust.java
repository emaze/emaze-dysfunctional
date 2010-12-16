package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.delegates.Delegate;

/**
 * Unary Delegate transforming a just(T) to T
 * @author dangelocola, rferranti
 */
public class FromJust<T> implements Delegate<T, Maybe<T>> {

    @Override
    public T perform(Maybe<T> element) {
        return element.value();
    }
}
