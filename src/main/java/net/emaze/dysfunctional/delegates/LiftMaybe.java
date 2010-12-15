package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author dangelocola
 */
public class LiftMaybe<T> implements Delegate<T, Maybe<T>> {

    @Override
    public T perform(Maybe<T> element) {
        return element.value();
    }
}
