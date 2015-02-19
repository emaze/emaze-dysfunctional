package net.emaze.dysfunctional.time;

import java.util.Date;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Supplier;

public class TimeStrategyToDateProvider implements Supplier<Date> {

    private TimeStrategy timeStrategy;

    public TimeStrategyToDateProvider(TimeStrategy timeStrategy) {
        dbc.precondition(timeStrategy != null, "cannot create a DateProvider with a null timeStrategy");
        this.timeStrategy = timeStrategy;
    }

    @Override
    public Date get() {
        return new TimeToDate().apply(timeStrategy.currentTime());
    }
}
