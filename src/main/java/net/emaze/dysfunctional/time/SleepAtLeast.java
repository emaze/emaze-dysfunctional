package net.emaze.dysfunctional.time;

import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.time.RealTimeStrategy.SleepInterruptedException;

/**
 *
 * @author rferranti
 */
public class SleepAtLeast implements Action<Long> {

    private final TimeStrategy time;

    public SleepAtLeast(TimeStrategy time) {
        this.time = time;
    }

    @Override
    public void perform(Long millis) {
        long elapsed = sleep(millis);
        while(elapsed < millis){
            elapsed+=sleep(millis - elapsed);
        }
    }

    private long sleep(long millis) {
        final long start = time.currentTimeMillis();
        try {
            time.sleep(millis);
            return millis;
        } catch (SleepInterruptedException ex) {
            final long now = time.currentTimeMillis();
            return now - start;
        }
    }
}
