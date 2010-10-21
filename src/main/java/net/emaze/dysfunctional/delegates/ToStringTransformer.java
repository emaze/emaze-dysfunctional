package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * A delegate transforming
 * @param <T>
 * @author rferranti
 */
public class ToStringTransformer<T> implements Delegate<String,T> {

    @Override
    public String perform(T element) {
        dbc.precondition(element != null, "passing a null element to a ToStringTransformer");
        return element.toString();
    }

}
