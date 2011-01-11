package net.emaze.dysfunctional.delegates;

import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryNoopTest {

    @Test
    public void canDoNothingWithNulls() {
        new TernaryNoop<Object, Object,Object>().perform(null,null, null);
    }
}