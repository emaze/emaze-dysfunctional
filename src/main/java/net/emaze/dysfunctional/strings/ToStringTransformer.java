package net.emaze.dysfunctional.strings;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * A delegate transforming
 * @param <T>
 * @author rferranti
 */
public class ToStringTransformer<T> implements Function<T, String> {

    @Override
    public String apply(T element) {
        dbc.precondition(element != null, "passing a null element to a ToStringTransformer");
        return element.toString();
    }

}
