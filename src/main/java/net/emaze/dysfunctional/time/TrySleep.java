package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;

/**
 *
 * @author rferranti
 */
public class TrySleep implements BinaryAction<Long, TimeUnit> {

    private final TimeStrategy time;

    public TrySleep(TimeStrategy time) {
        this.time = time;
    }

    @Override
    public void perform(Long duration, TimeUnit unit) {
        try {
            time.sleep(duration, unit);
        } catch (SleepInterruptedException ex) {
        }
    }
}
