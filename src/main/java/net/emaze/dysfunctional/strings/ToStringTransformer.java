package net.emaze.dysfunctional.strings;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

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
