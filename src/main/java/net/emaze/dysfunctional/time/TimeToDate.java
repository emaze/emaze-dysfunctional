package net.emaze.dysfunctional.time;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.tuples.Pair;

public class TimeToDate implements Function<Pair<Long, TimeUnit>, Date> {

    @Override
    public Date apply(Pair<Long, TimeUnit> time) {
        dbc.precondition(time != null, "cannot convert null time to date");
        dbc.precondition(time.first() != null, "cannot transform time to date using a null duration");
        return new Date(TimeUnit.MILLISECONDS.convert(time.first(), time.second()));
    }
}
