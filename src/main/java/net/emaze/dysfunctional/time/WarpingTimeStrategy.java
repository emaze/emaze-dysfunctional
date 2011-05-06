package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class WarpingTimeStrategy implements TimeStrategy {

    private long now;

    public WarpingTimeStrategy(long now) {
        this.now = now;
    }

    @Override
    public Pair<Long, TimeUnit> currentTime() {
        return Pair.of(now, TimeUnit.MILLISECONDS);
    }

    @Override
    public void sleep(long duration, TimeUnit unit) {
        now += unit.toMillis(duration);
    }

    public void warp(long sinceEpoch, TimeUnit unit) {
        now = unit.toMillis(sinceEpoch);
    }
}
