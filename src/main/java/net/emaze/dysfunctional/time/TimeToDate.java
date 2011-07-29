package net.emaze.dysfunctional.time;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.tuples.Pair;

public class TimeToDate implements Delegate<Date, Pair<Long, TimeUnit>> {

    @Override
    public Date perform(Pair<Long, TimeUnit> time) {
        dbc.precondition(time != null, "cannot convert null time to date");
        dbc.precondition(time.first() != null, "cannot transform time to date using a null duration");
        return new Date(TimeUnit.MILLISECONDS.convert(time.first(), time.second()));
    }
}
