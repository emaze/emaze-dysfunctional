package net.emaze.dysfunctional.strings;

import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class ToUpperCase implements UnaryOperator<String> {

    @Override
    public String apply(String str) {
        dbc.precondition(str != null, "cannot uppercase a null string");
        return str.toUpperCase();
    }

}
