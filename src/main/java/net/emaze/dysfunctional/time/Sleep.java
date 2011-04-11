package net.emaze.dysfunctional.time;

import net.emaze.dysfunctional.dispatching.actions.Action;

/**
 *
 * @author rferranti
 */
public class Sleep implements Action<Long> {

    @Override
    public void perform(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
        }
    }
}
