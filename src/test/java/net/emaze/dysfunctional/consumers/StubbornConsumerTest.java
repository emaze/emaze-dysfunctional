package net.emaze.dysfunctional.consumers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StubbornConsumerTest {

    @Test(expected=IllegalArgumentException.class)
    public void consumingNullIteratorYieldsException() {
        new StubbornConsumer<Object>().consume(null);
    }

    @Test
    public void yieldsLastElement(){
        List<Integer> consumable = Arrays.asList(1,2,3);
        StubbornConsumer<Integer> consumer = new StubbornConsumer<Integer>();
        Integer got = consumer.consume(consumable.iterator());
        Assert.assertEquals(consumable.get(consumable.size() -1 ), got);
    }
    @Test(expected=IllegalArgumentException.class)
    public void consumingEmptyIteratorYieldsException(){
        new StubbornConsumer<Object>().consume(Collections.emptyList().iterator());
    }

}