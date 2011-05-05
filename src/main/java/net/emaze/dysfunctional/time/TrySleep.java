package net.emaze.dysfunctional.time;

import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.time.RealTimeStrategy.SleepInterruptedException;

/**
 *
 * @author rferranti
 */
public class TrySleep implements Action<Long> {

    private final TimeStrategy time;

    public TrySleep(TimeStrategy time) {
        this.time = time;
    }

    @Override
    public void perform(Long millis) {
        try {
            time.sleep(millis);
        } catch (SleepInterruptedException ex) {
        }
    }
}
