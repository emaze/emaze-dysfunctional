package net.emaze.dysfunctional.strings;

import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class ToLowerCase implements UnaryOperator<String> {

    @Override
    public String apply(String str) {
        dbc.precondition(str != null, "cannot lowercase a null string");
        return str.toLowerCase();
    }

}
