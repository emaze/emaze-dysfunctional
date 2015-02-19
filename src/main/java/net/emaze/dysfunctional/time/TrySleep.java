package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 *
 * @author rferranti
 */
public class TrySleep implements BiConsumer<Long, TimeUnit> {

    private final TimeStrategy time;

    public TrySleep(TimeStrategy time) {
        this.time = time;
    }

    @Override
    public void accept(Long duration, TimeUnit unit) {
        try {
            time.sleep(duration, unit);
        } catch (SleepInterruptedException ex) {
        }
    }
}
