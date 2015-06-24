package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * No, it doesn't parse two times.
 * @author rferranti
 */
public class DoubleParser implements Function<String, Double> {

    @Override
    public Double apply(String parsee) {
        dbc.precondition(parsee != null, "cannot parse a null string");
        return Double.parseDouble(parsee);
    }
}
