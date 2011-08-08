package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.tuples.Pair;

public class TimeStrategyToMillisProvider implements Provider<Long> {

    private final TimeStrategy timeStrategy;

    public TimeStrategyToMillisProvider(TimeStrategy timeStrategy) {
        dbc.precondition(timeStrategy != null, "cannot create a MillisProvider with a null timeStrategy");
        this.timeStrategy = timeStrategy;
    }

    @Override
    public Long provide() {
        final Pair<Long, TimeUnit> currentTime = timeStrategy.currentTime();
        return TimeUnit.MILLISECONDS.convert(currentTime.first(), currentTime.second());
    }
}
