package net.emaze.dysfunctional.strings.lexcasts;

import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

public class BooleanParser implements Delegate<Boolean, String> {

    private static final List<String> acceptedValues = Arrays.asList("true", "false");

    @Override
    public Boolean perform(String parsee) {
        dbc.precondition(parsee != null, "cannot parse a null string");
        dbc.precondition(acceptedValues.contains(parsee.toLowerCase()), "cannot parse string '%s' to boolean", parsee);
        return Boolean.parseBoolean(parsee);
    }
}
