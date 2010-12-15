package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.options.Maybe;

/**
 * Unary delegate that calls <code>value()</code> from <code>Maybe</code>.
 * 
 * @author dangelocola
 */
public class FromJust<T> implements Delegate<T, Maybe<T>> {

    @Override
    public T perform(Maybe<T> element) {
        return element.value();
    }
}
