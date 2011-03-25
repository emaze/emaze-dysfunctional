package net.emaze.dysfunctional.time;

import java.util.Date;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class LongToDate implements Delegate<Date, Long>{

    @Override
    public Date perform(Long timestamp) {
        dbc.precondition(timestamp != null, "cannot convert null long to date");
        return new Date(timestamp);
    }

}
