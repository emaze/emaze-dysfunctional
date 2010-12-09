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
public class GiveUpConsumerTest {


    @Test(expected=IllegalArgumentException.class)
    public void consumingNullIteratorYieldsException() {
        new GiveUpConsumer<Object>().consume(null);
    }

    @Test
    public void yieldsFirstElement(){
        List<Integer> consumable = Arrays.asList(1,2,3);
        GiveUpConsumer<Integer> consumer = new GiveUpConsumer<Integer>();
        Integer got = consumer.consume(consumable.iterator());
        Assert.assertEquals(consumable.get(0), got);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void consumingEmptyIteratorYieldsException(){
        new GiveUpConsumer<Object>().consume(Collections.emptyList().iterator());
    }
}