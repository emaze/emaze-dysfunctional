package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 *
 * @author rferranti
 */
public class FloatParser implements Function<String, Float> {

    @Override
    public Float apply(String parsee) {
        dbc.precondition(parsee != null, "cannot parse a null string");
        return Float.parseFloat(parsee);
    }
}
