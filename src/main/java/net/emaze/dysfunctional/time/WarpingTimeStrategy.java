package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class WarpingTimeStrategy implements TimeStrategy {

    private final WarpingKnobs knobs;

    public WarpingTimeStrategy(WarpingKnobs knobs) {
        this.knobs = knobs;
    }

    @Override
    public Pair<Long, TimeUnit> currentTime() {
        return knobs.state();
    }

    @Override
    public void sleep(long duration, TimeUnit unit) {
        knobs.add(duration, unit);
    }
}
