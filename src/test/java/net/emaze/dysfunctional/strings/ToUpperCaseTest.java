package net.emaze.dysfunctional.strings;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ToUpperCaseTest {

    @Test(expected=IllegalArgumentException.class)
    public void uppercasingNullYieldsException() {
        new ToUpperCase().perform(null);
    }

    @Test
    public void canUpperCaseString(){
        Assert.assertEquals("A", new ToUpperCase().perform("a"));
    }
}