package net.emaze.dysfunctional.strings;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 *
 * @author rferranti
 */
public class ToUpperCase implements Function<String, String>{

    @Override
    public String apply(String str) {
        dbc.precondition(str != null, "cannot uppercase a null string");
        return str.toUpperCase();
    }

}
