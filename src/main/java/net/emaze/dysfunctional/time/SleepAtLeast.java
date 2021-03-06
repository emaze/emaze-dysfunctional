package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class SleepAtLeast implements BiConsumer<Long, TimeUnit> {

    private final TimeStrategy time;

    public SleepAtLeast(TimeStrategy time) {
        this.time = time;
    }

    @Override
    public void accept(Long duration, TimeUnit unit) {
        Pair<Long, TimeUnit> slept = sleep(duration, unit);
        long elapsedInStrategyUnit = slept.first();
        while (elapsedInStrategyUnit < slept.second().convert(duration, unit)) {
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
