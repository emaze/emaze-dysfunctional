package net.emaze.dysfunctional.delegates;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ClassPluckerTest {

    @Test(expected=IllegalArgumentException.class)
    public void pluckingClassFromNullYieldsException() {
        new ClassPlucker<Object>().perform(null);
    }

    @Test
    public void canPluckClass() {
        final Class<?> got = new ClassPlucker<Object>().perform(new Object());
        Assert.assertEquals(Object.class, got);
    }

}