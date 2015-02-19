package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 *
 * @author rferranti
 */
public class Sleep implements BiConsumer<Long, TimeUnit> {

    private final TimeStrategy time;

    public Sleep(TimeStrategy time) {
        this.time = time;
    }
    
    @Override
    public void accept(Long duration, TimeUnit unit) {
        time.sleep(duration, unit);
    }
}
