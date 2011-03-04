package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryIdentityTest {

    @Test
    public void identitiesOfNullsAreNulls() {
        final Triple<O, O, O> got = new TernaryIdentity<O, O, O>().perform(null, null, null);
        Assert.assertEquals(new Triple<O, O, O>(null, null, null), got);
    }
}
