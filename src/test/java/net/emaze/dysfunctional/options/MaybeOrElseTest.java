package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MaybeOrElseTest {

    private MaybeOrElse<O> UNDER_TEST = new MaybeOrElse<O>();

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullMaybeYieldsException() {
        UNDER_TEST.perform(null, O.ONE);
    }

    @Test
    public void callingWithJustYieldsJustValue() {
        final O got = UNDER_TEST.perform(Maybe.just(O.ONE), O.ANOTHER);
        Assert.assertEquals(O.ONE, got);
    }

    @Test
    public void callingWithNothingYieldsSecondParameter() {
        final O got = UNDER_TEST.perform(Maybe.<O>nothing(), O.ANOTHER);
        Assert.assertEquals(O.ANOTHER, got);
    }
}
