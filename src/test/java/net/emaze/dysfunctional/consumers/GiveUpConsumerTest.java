package net.emaze.dysfunctional.consumers;

import net.emaze.dysfunctional.searches.FirstElement;
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
        new FirstElement<Object>().perform(null);
    }

    @Test
    public void yieldsFirstElement(){
        List<Integer> consumable = Arrays.asList(1,2,3);
        FirstElement<Integer> consumer = new FirstElement<Integer>();
        Integer got = consumer.perform(consumable.iterator());
        Assert.assertEquals(consumable.get(0), got);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void consumingEmptyIteratorYieldsException(){
        new FirstElement<Object>().perform(Collections.emptyList().iterator());
    }
}