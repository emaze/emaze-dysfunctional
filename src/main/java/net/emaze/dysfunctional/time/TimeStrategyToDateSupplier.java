package net.emaze.dysfunctional.time;

import java.util.Date;
import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;

public class TimeStrategyToDateSupplier implements Supplier<Date> {

    private TimeStrategy timeStrategy;

    public TimeStrategyToDateSupplier(TimeStrategy timeStrategy) {
        dbc.precondition(timeStrategy != null, "cannot create a DateSupplier with a null timeStrategy");
        this.timeStrategy = timeStrategy;
    }

    @Override
    public Date get() {
        return new TimeToDate().apply(timeStrategy.currentTime());
    }
}
