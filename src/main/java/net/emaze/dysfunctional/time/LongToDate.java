package net.emaze.dysfunctional.time;

import java.util.Date;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 *
 * @author rferranti
 */
public class LongToDate implements Function<Long, Date> {

    @Override
    public Date apply(Long timestamp) {
        dbc.precondition(timestamp != null, "cannot convert null long to date");
        return new Date(timestamp);
    }
}
