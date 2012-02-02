package net.emaze.dysfunctional.consumers;

import net.emaze.dysfunctional.dispatching.delegates.LastElement;
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
        new LastElement<Object>().perform(null);
    }

    @Test
    public void yieldsLastElement(){
        List<Integer> consumable = Arrays.asList(1,2,3);
        LastElement<Integer> consumer = new LastElement<Integer>();
        Integer got = consumer.perform(consumable.iterator());
        Assert.assertEquals(consumable.get(consumable.size() -1 ), got);
    }
    @Test(expected=IllegalArgumentException.class)
    public void consumingEmptyIteratorYieldsException(){
        new LastElement<Object>().perform(Collections.emptyList().iterator());
    }

}