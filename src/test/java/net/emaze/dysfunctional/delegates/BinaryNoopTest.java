package net.emaze.dysfunctional.delegates;

import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryNoopTest {

    @Test
    public void canDoNothingWithNulls() {
        new BinaryNoop<Object,Object>().perform(null,null);
    }

}