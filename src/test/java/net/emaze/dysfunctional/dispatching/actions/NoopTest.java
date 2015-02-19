package net.emaze.dysfunctional.dispatching.actions;

import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class NoopTest {

    @Test
    public void canDoNothingWithNull() {
        new Noop<Object>().accept(null);
    }

}