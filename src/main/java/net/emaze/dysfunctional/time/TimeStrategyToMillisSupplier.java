package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.tuples.Pair;

public class TimeStrategyToMillisSupplier implements Supplier<Long> {

    private final TimeStrategy timeStrategy;

    public TimeStrategyToMillisSupplier(TimeStrategy timeStrategy) {
        dbc.precondition(timeStrategy != null, "cannot create a MillisSupplier with a null timeStrategy");
        this.timeStrategy = timeStrategy;
    }

    @Override
    public Long get() {
        final Pair<Long, TimeUnit> currentTime = timeStrategy.currentTime();
        return TimeUnit.MILLISECONDS.convert(currentTime.first(), currentTime.second());
    }
}
