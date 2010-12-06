package net.emaze.dysfunctional.consumers;

import net.emaze.dysfunctional.adapting.ArrayIterator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CountingConsumerTest {

    @Test
    public void canCountAnIterator() {
        Integer[] arr = new Integer[]{1, 2, 3, 4};
        CountingConsumer<Integer> cc = new CountingConsumer<Integer>();
        long count = cc.consume(new ArrayIterator<Integer>(arr));
        Assert.assertEquals(4, count);
    }


}