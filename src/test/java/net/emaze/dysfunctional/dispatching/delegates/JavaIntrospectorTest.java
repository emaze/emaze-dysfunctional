package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.dispatching.delegates.JavaIntrospector;
import java.beans.PropertyDescriptor;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class JavaIntrospectorTest {

    @Test
    public void canIntrospectBean() {
        final PropertyDescriptor[] properties = new JavaIntrospector().perform(SimpleBean.class, Object.class);
        Assert.assertEquals(1, properties.length);
    }
    
    @Test(expected=IllegalStateException.class)
    public void introspectingWithWrongBaseClassYieldsException() {
        new JavaIntrospector().perform(SimpleBean.class, UnrelatedBean.class);
    }

    public static class SimpleBean {

        public O getO() {
            return O.ONE;
        }
    }

    public static class UnrelatedBean {

    }
}
