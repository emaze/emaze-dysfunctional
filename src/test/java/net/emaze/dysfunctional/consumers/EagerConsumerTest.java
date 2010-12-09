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
public class EagerConsumerTest {

    @Test
    public void consumingEmptyIteratorYieldsEmptyList() {
        List<Integer> consumable = Collections.emptyList();
        final EagerConsumer<Integer> cons = new EagerConsumer<Integer>();
        List<Integer> got = cons.consume(consumable.iterator());
        Assert.assertEquals(consumable, got);
    }

    @Test
    public void consumingListYieldsSameValuesAsInList() {
        List<Integer> consumable = Arrays.asList(1, 2, 3);
        final EagerConsumer<Integer> cons = new EagerConsumer<Integer>();
        List<Integer> got = cons.consume(consumable.iterator());
        Assert.assertEquals(consumable, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void consumingNullIteratorYieldException() {
        new EagerConsumer<Integer>().consume(null);
    }
}
