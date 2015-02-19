package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.dispatching.delegates.ClassPlucker;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ClassPluckerTest {

    @Test(expected=IllegalArgumentException.class)
    public void pluckingClassFromNullYieldsException() {
        new ClassPlucker<Object>().apply(null);
    }

    @Test
    public void canPluckClass() {
        final Class<?> got = new ClassPlucker<Object>().apply(new Object());
        Assert.assertEquals(Object.class, got);
    }

}