package net.emaze.dysfunctional.strings;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 *
 * @author rferranti
 */
public class ToLowerCase implements Function<String, String>{

    @Override
    public String apply(String str) {
        dbc.precondition(str != null, "cannot lowercase a null string");
        return str.toLowerCase();
    }

}
