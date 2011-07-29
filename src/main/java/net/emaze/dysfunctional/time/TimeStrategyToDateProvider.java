package net.emaze.dysfunctional.time;

import java.util.Date;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

public class TimeStrategyToDateProvider implements Provider<Date> {

    private TimeStrategy timeStrategy;

    public TimeStrategyToDateProvider(TimeStrategy timeStrategy) {
        dbc.precondition(timeStrategy != null, "cannot create a DateProvider with a null timeStrategy");
        this.timeStrategy = timeStrategy;
    }

    @Override
    public Date provide() {
        return new TimeToDate().perform(timeStrategy.currentTime());
    }
}
