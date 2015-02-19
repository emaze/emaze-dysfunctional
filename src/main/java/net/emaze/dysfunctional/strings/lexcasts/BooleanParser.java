package net.emaze.dysfunctional.strings.lexcasts;

import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

public class BooleanParser implements Function<String, Boolean> {

    private static final List<String> ACCEPTED_VALUES = Arrays.asList("true", "false");

    @Override
    public Boolean apply(String parsee) {
        dbc.precondition(parsee != null, "cannot parse a null string");
        dbc.precondition(ACCEPTED_VALUES.contains(parsee.toLowerCase()), "cannot parse string '%s' to boolean", parsee);
        return Boolean.parseBoolean(parsee);
    }
}
