package net.emaze.dysfunctional.collections;

import java.util.TreeMap;
import junit.framework.Assert;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

public class TreeMapFactoryTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void creatingTreeMapFactoryWithNullComparatorYieldsException() {
        new TreeMapFactory<O, O>(null);
    }

    @Test
    public void callingCreatesATreeMap() {
        final TreeMapFactory<Integer, Integer> factory = new TreeMapFactory<Integer, Integer>(new ComparableComparator<Integer>());
        final TreeMap<Integer, Integer> got = factory.get();
        Assert.assertNotNull(got);
    }
}
