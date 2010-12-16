package net.emaze.dysfunctional.options;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FromJustTest {

    @Test(expected=IllegalStateException.class)
    public void transformingNothingYieldsException() {
        new FromJust<Object>().perform(Maybe.nothing());
    }
    
    @Test
    public void canFetchValueFromJust() {
        Maybe<Integer> maybeInt = Maybe.just(1);
        int got = new FromJust<Integer>().perform(maybeInt);
        Assert.assertEquals(1, got);
    }

}