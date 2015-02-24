package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryNoopTest {

    @Test
    public void canDoNothingWithNulls() {
        new TernaryNoop<O, O, O>().accept(null, null, null);
    }
}
