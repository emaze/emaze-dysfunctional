package net.emaze.dysfunctional.time;

import java.util.Date;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 *
 * @author rferranti
 */
public class DateToLong implements Function<Date, Long> {

    @Override
    public Long apply(Date date) {
        dbc.precondition(date != null, "cannot convert null date to long");
        return date.getTime();
    }
}
