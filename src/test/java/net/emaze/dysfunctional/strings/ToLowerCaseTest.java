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
        new ToLowerCase().apply(null);
    }

    @Test
    public void canLowerCaseString(){
        Assert.assertEquals("a", new ToLowerCase().apply("A"));
    }
}