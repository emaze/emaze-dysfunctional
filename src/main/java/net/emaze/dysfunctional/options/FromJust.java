package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Unary Delegate transforming a just(T) to T
 * @author dangelocola, rferranti
 */
public class FromJust<T> implements Delegate<T, Maybe<T>> {

    @Override
    public T perform(Maybe<T> element) {
        dbc.precondition(element != null, "performing FromJust against null");
        return element.value();
    }
}
