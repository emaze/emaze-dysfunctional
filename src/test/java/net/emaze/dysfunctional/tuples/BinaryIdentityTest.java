package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryIdentityTest {

    @Test
    public void identitiesOfNullsAreNulls() {
        final Pair<O, O> got = new BinaryIdentity<O, O>().perform(null, null);
        Assert.assertEquals(new Pair<O,O>(null, null), got);
    }

}