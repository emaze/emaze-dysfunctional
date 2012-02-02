package net.emaze.dysfunctional.groups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.Compositions;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class GroupByTest {

    private final Provider<HashMap<O, List<O>>> MAP_FACTORY = new HashMapFactory<O, List<O>>();
    private final Provider<List<O>> LIST_FACTORY = Compositions.compose(new Narrow<List<O>, ArrayList<O>>(), new ArrayListFactory<O>());
    private final Delegate<O, O> GROUPER = new Identity<O>();

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullGrouperYieldsException() {
        new GroupBy<HashMap<O, List<O>>, List<O>, O, O>(null, LIST_FACTORY, MAP_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullListProviderYieldsException() {
        new GroupBy<HashMap<O, List<O>>, List<O>, O, O>(GROUPER, null, MAP_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullMapProviderYieldsException() {
        new GroupBy<HashMap<O, List<O>>, List<O>, O, O>(GROUPER, LIST_FACTORY, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void groupingNullIteratorYieldsException() {
        final GroupBy<HashMap<O, List<O>>, List<O>, O, O> groupBy = new GroupBy<HashMap<O, List<O>>, List<O>, O, O>(GROUPER, LIST_FACTORY, MAP_FACTORY);
        groupBy.perform(null);
    }

    @Test
    public void groupingAnElementYieldsTheElementInValues() {
        final GroupBy<HashMap<O, List<O>>, List<O>, O, O> groupBy = new GroupBy<HashMap<O, List<O>>, List<O>, O, O>(GROUPER, LIST_FACTORY, MAP_FACTORY);
        final Iterator<O> groupies = Iterations.iterator(O.ONE);
        HashMap<O, List<O>> got = groupBy.perform(groupies);
        Assert.assertEquals(Arrays.asList(O.ONE), got.get(O.ONE));
    }

    @Test
    public void elementsGroupedTogetherAreInTheSameList() {
        final GroupBy<HashMap<O, List<O>>, List<O>, O, O> groupBy = new GroupBy<HashMap<O, List<O>>, List<O>, O, O>(GROUPER, LIST_FACTORY, MAP_FACTORY);
        final Iterator<O> groupies = Iterations.iterator(O.ONE, O.ONE);
        HashMap<O, List<O>> got = groupBy.perform(groupies);
        Assert.assertEquals(Arrays.asList(O.ONE, O.ONE), got.get(O.ONE));
    }
}
