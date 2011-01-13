package net.emaze.dysfunctional.consumers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.collections.ArrayListFactory;
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
        final EagerConsumer<ArrayList<Integer>, Integer> cons = new EagerConsumer<ArrayList<Integer>, Integer>(new ArrayListFactory<Integer>());
        ArrayList<Integer> got = cons.consume(consumable.iterator());
        Assert.assertEquals(consumable, got);
    }

    @Test
    public void consumingListYieldsSameValuesAsInList() {
        List<Integer> consumable = Arrays.asList(1, 2, 3);
        final EagerConsumer<ArrayList<Integer>, Integer> cons = new EagerConsumer<ArrayList<Integer>, Integer>(new ArrayListFactory<Integer>());
        ArrayList<Integer> got = cons.consume(consumable.iterator());
        Assert.assertEquals(consumable, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void consumingNullIteratorYieldException() {
        final EagerConsumer<ArrayList<Integer>, Integer> cons = new EagerConsumer<ArrayList<Integer>, Integer>(new ArrayListFactory<Integer>());
        cons.consume(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void creatingConsumerWithNullFactorYieldsException() {
        new EagerConsumer<ArrayList<Integer>, Integer>(null);

    }
}
