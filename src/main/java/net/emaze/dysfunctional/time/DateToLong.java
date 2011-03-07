package net.emaze.dysfunctional.time;

import java.util.Date;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class DateToLong implements Delegate<Long, Date> {

    @Override
    public Long perform(Date date) {
        dbc.precondition(date != null, "cannot convert null date to long");
        return date.getTime();
    }
}
