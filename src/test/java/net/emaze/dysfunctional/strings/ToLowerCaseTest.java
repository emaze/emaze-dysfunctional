package net.emaze.dysfunctional.strings;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ToLowerCaseTest {

    @Test(expected=IllegalArgumentException.class)
    public void lowercasingNullYieldsException() {
        new ToLowerCase().perform(null);
    }

    @Test
    public void canLowerCaseString(){
        Assert.assertEquals("a", new ToLowerCase().perform("A"));
    }
}