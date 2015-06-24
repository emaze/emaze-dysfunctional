package net.emaze.dysfunctional.strings;

import net.emaze.dysfunctional.strings.ToStringTransformer;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ToStringTransformerTest {

    @Test(expected=IllegalArgumentException.class)
    public void transformingNullYieldsException() {
        new ToStringTransformer<Object>().apply(null);
    }
    @Test
    public void canTransformAnObject() {
        final ToStringTransformer<Object> tr = new ToStringTransformer<Object>();
        final String got = tr.apply(new Object(){

            @Override
            public String toString() {
                return "something";
            }

        });
        Assert.assertEquals("something", got);
    }

}