package net.emaze.dysfunctional.options;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FromJustTest {

    @Test(expected=NoSuchElementException.class)
    public void transformingNothingYieldsException() {
        new FromJust<>().apply(Optional.empty());
    }
    
    @Test
    public void canFetchValueFromJust() {
        Optional<Integer> maybeInt = Optional.of(1);
        int got = new FromJust<Integer>().apply(maybeInt);
        Assert.assertEquals(1, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fetchingFromNullYieldsException() {
        new FromJust<Object>().apply(null);
    }
}