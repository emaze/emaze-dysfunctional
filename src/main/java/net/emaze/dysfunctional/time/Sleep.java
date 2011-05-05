package net.emaze.dysfunctional.time;

import net.emaze.dysfunctional.dispatching.actions.Action;

/**
 *
 * @author rferranti
 */
public class Sleep implements Action<Long> {

    private final TimeStrategy time;

    public Sleep(TimeStrategy time) {
        this.time = time;
    }
    
    @Override
    public void perform(Long millis) {
        time.sleep(millis);
    }
}
