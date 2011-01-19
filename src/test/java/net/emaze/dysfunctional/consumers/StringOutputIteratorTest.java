package net.emaze.dysfunctional.consumers;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StringOutputIteratorTest {

    @Test
    public void testCanAppendAString() {
        final
        StringOutputIterator iter = new StringOutputIterator();
        iter.next("1");
        iter.next("2");
        iter.next("3");
        Assert.assertEquals("123", iter.toString());
    }

}