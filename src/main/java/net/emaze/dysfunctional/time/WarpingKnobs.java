package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class WarpingKnobs {

    private AtomicLong now = new AtomicLong();

    public Pair<Long, TimeUnit> state() {
        return Pair.of(now.get(), TimeUnit.MILLISECONDS);
    }

    public void warp(long sinceEpoch, TimeUnit unit) {
        dbc.precondition(unit != null, "cannot warp with a null TimeUnit");
        now.set(unit.toMillis(sinceEpoch));
    }

    public void add(long quantity, TimeUnit unit) {
        dbc.precondition(unit != null, "cannot add with a null TimeUnit");
        now.addAndGet(unit.toMillis(quantity));
    }
}
