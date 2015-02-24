package net.emaze.dysfunctional.groups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import net.emaze.dysfunctional.Compositions;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class GroupByTest {

    private final Supplier<HashMap<O, List<O>>> MAP_FACTORY = new HashMapFactory<O, List<O>>();
    private final Supplier<List<O>> LIST_FACTORY = Compositions.compose(new Vary<ArrayList<O>, List<O>>(), new ArrayListFactory<O>());
    private final Function<O, O> GROUPER = Function.identity();

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
        groupBy.apply(null);
    }

    @Test
    public void groupingAnElementYieldsTheElementInValues() {
        final GroupBy<HashMap<O, List<O>>, List<O>, O, O> groupBy = new GroupBy<HashMap<O, List<O>>, List<O>, O, O>(GROUPER, LIST_FACTORY, MAP_FACTORY);
        final Iterator<O> groupies = Iterations.iterator(O.ONE);
        HashMap<O, List<O>> got = groupBy.apply(groupies);
        Assert.assertEquals(Arrays.asList(O.ONE), got.get(O.ONE));
    }

    @Test
    public void elementsGroupedTogetherAreInTheSameList() {
        final GroupBy<HashMap<O, List<O>>, List<O>, O, O> groupBy = new GroupBy<HashMap<O, List<O>>, List<O>, O, O>(GROUPER, LIST_FACTORY, MAP_FACTORY);
        final Iterator<O> groupies = Iterations.iterator(O.ONE, O.ONE);
        HashMap<O, List<O>> got = groupBy.apply(groupies);
        Assert.assertEquals(Arrays.asList(O.ONE, O.ONE), got.get(O.ONE));
    }
}
