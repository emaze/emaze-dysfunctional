package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class SleepAtLeast implements BinaryAction<Long, TimeUnit> {

    private final TimeStrategy time;

    public SleepAtLeast(TimeStrategy time) {
        this.time = time;
    }

    @Override
    public void perform(Long duration, TimeUnit unit) {
        Pair<Long, TimeUnit> slept = sleep(duration, unit);
        long elapsedInStrategyUnit = slept.first();
        while (elapsedInStrategyUnit < duration) {
            slept = sleep(slept.second().convert(duration, unit) - elapsedInStrategyUnit, unit);
            elapsedInStrategyUnit += slept.first();
        }
    }

    private Pair<Long, TimeUnit> sleep(long duration, TimeUnit unit) {
        final Pair<Long, TimeUnit> start = time.currentTime();
        try {
            time.sleep(duration, unit);
            return Pair.of(start.second().convert(duration, unit), start.second());
        } catch (SleepInterruptedException ex) {
            final Pair<Long, TimeUnit> now = time.currentTime();
            return Pair.of(now.first() - start.first(), now.second());
        }
    }
}
