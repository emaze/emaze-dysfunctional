package net.emaze.dysfunctional.consumers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import java.util.function.Function;
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
        final ConsumeIntoCollection<Integer, ArrayList<Integer>> cons = new ConsumeIntoCollection<>(new ArrayListFactory<Integer>());
        ArrayList<Integer> got = cons.apply(consumable.iterator());
        Assert.assertEquals(consumable, got);
    }

    @Test
    public void consumingListYieldsSameValuesAsInList() {
        List<Integer> consumable = Arrays.asList(1, 2, 3);
        final ConsumeIntoCollection<Integer, ArrayList<Integer>> cons = new ConsumeIntoCollection<>(new ArrayListFactory<Integer>());
        ArrayList<Integer> got = cons.apply(consumable.iterator());
        Assert.assertEquals(consumable, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void consumingNullIteratorYieldException() {
        final ConsumeIntoCollection<Integer, ArrayList<Integer>> cons = new ConsumeIntoCollection<>(new ArrayListFactory<Integer>());
        cons.apply(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingConsumerWithNullFactorYieldsException() {
        new ConsumeIntoCollection<>(null);

    }

    @Test(expected = ClassCastException.class)
    public void consumingFromErasureWithWrongTypeYieldsException() {
        final Function cons = new ConsumeIntoCollection<>(new ArrayListFactory<Integer>());
        cons.apply(new Object());
    }
}
