package net.emaze.dysfunctional.delegates;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ToStringTransformerTest {

    @Test(expected=IllegalArgumentException.class)
    public void transformingNullYieldsException() {
        new ToStringTransformer<Object>().perform(null);
    }
    @Test
    public void canTransformAnObject() {
        final ToStringTransformer<Object> tr = new ToStringTransformer<Object>();
        final String got = tr.perform(new Object(){

            @Override
            public String toString() {
                return "something";
            }

        });
        Assert.assertEquals("something", got);
    }

}