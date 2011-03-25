package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
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