package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 *
 * @author rferranti
 */
public class IntegerParser implements Function<String, Integer> {

    private final int radix;

    public IntegerParser(int radix) {
        dbc.precondition(radix >= Character.MIN_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");
        dbc.precondition(radix <= Character.MAX_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");        
        this.radix = radix;
    }

    @Override
    public Integer apply(String parsee) {
        dbc.precondition(parsee != null, "cannot parse a null string");
        return Integer.parseInt(parsee, radix);
    }
}
