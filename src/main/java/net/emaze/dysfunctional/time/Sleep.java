package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;

/**
 *
 * @author rferranti
 */
public class Sleep implements BinaryAction<Long, TimeUnit> {

    private final TimeStrategy time;

    public Sleep(TimeStrategy time) {
        this.time = time;
    }
    
    @Override
    public void perform(Long duration, TimeUnit unit) {
        time.sleep(duration, unit);
    }
}
