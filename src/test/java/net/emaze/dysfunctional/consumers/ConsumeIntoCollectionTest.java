package net.emaze.dysfunctional.consumers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConsumeIntoCollectionTest {

    @Test
    public void consumingEmptyIteratorYieldsEmptyList() {
        List<Integer> consumable = Collections.emptyList();
        final ConsumeIntoCollection<ArrayList<Integer>, Integer> cons = new ConsumeIntoCollection<ArrayList<Integer>, Integer>(new ArrayListFactory<Integer>());
        ArrayList<Integer> got = cons.perform(consumable.iterator());
        Assert.assertEquals(consumable, got);
    }

    @Test
    public void consumingListYieldsSameValuesAsInList() {
        List<Integer> consumable = Arrays.asList(1, 2, 3);
        final ConsumeIntoCollection<ArrayList<Integer>, Integer> cons = new ConsumeIntoCollection<ArrayList<Integer>, Integer>(new ArrayListFactory<Integer>());
        ArrayList<Integer> got = cons.perform(consumable.iterator());
        Assert.assertEquals(consumable, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void consumingNullIteratorYieldException() {
        final ConsumeIntoCollection<ArrayList<Integer>, Integer> cons = new ConsumeIntoCollection<ArrayList<Integer>, Integer>(new ArrayListFactory<Integer>());
        cons.perform(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingConsumerWithNullFactorYieldsException() {
        new ConsumeIntoCollection<ArrayList<Integer>, Integer>(null);

    }

    @Test(expected = ClassCastException.class)
    public void consumingFromErasureWithWrongTypeYieldsException() {
        final Delegate cons = new ConsumeIntoCollection<ArrayList<Integer>, Integer>(new ArrayListFactory<Integer>());
        cons.perform(new Object());
    }
}
