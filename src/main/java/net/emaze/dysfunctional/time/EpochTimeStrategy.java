package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class EpochTimeStrategy implements TimeStrategy {

    @Override
    public Pair<Long, TimeUnit> currentTime() {
        return Pair.of(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void sleep(long duration, TimeUnit unit) {
        try {
            Thread.sleep(unit.toMillis(duration));
        } catch (InterruptedException ex) {
            throw new SleepInterruptedException(ex);
        }
    }
}
