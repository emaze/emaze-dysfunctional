package net.emaze.dysfunctional.delegates;

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