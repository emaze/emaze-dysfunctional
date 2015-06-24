package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.Optional;

/**
 *
 * @author rferranti
 */
public class LongTryParser implements Function<String, Optional<Long>> {

    private final int radix;

    public LongTryParser(int radix) {
        dbc.precondition(radix >= Character.MIN_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");
        dbc.precondition(radix <= Character.MAX_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");        
        this.radix = radix;
    }

    @Override
    public Optional<Long> apply(String parsee) {
        try {
            return Optional.of(Long.parseLong(parsee, radix));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }
}
