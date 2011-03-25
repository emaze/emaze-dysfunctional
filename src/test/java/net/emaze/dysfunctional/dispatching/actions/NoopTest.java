package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.dispatching.actions.Noop;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class NoopTest {

    @Test
    public void canDoNothingWithNull() {
        new Noop<Object>().perform(null);
    }

}