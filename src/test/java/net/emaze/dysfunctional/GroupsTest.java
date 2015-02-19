package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.collections.HashSetFactory;
import net.emaze.dysfunctional.collections.LinkedHashMapFactory;
import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.GroupsTest.IndexByTest;
import net.emaze.dysfunctional.GroupsTest.FacadeTest;
import net.emaze.dysfunctional.GroupsTest.GroupByTest;
import net.emaze.dysfunctional.GroupsTest.PartitionByTest;
import net.emaze.dysfunctional.testing.O;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    GroupByTest.class,
    PartitionByTest.class,
    IndexByTest.class,
    FacadeTest.class})
public class GroupsTest {

    private static final ArrayListFactory<O> LIST_FACTORY = new ArrayListFactory<O>();
    private static final HashSetFactory<O> SET_FACTORY = new HashSetFactory<O>();
    private static final HashMapFactory<O, ArrayList<O>> MAP_FACTORY = new HashMapFactory<O, ArrayList<O>>();

    public static class GroupByTest {

        @Test
        public void canGroupFromAnIterable() {
            final Iterable<O> values = Iterations.iterable(O.ONE);
            final Map<O, List<O>> grouped = Groups.groupBy(values, new Identity<O>());
            Assert.assertEquals(Arrays.asList(O.ONE), grouped.get(O.ONE));
        }

        @Test
        public void canGroupFromAnIterator() {
            final Iterator<O> values = Iterations.iterator(O.ONE);
            final Map<O, List<O>> grouped = Groups.groupBy(values, new Identity<O>());
            Assert.assertEquals(Arrays.asList(O.ONE), grouped.get(O.ONE));
        }

        @Test
        public void canGroupFromAnArray() {
            final O[] values = {O.ONE};
            final Map<O, List<O>> grouped = Groups.groupBy(values, new Identity<O>());
            Assert.assertEquals(Arrays.asList(O.ONE), grouped.get(O.ONE));
        }

        @Test
        public void canGroupFromAnIterableUsingProvider() {
            final Iterable<O> values = Iterations.iterable(O.ONE);
            final Map<O, ArrayList<O>> grouped = Groups.groupBy(values, new Identity<O>(), LIST_FACTORY);
            Assert.assertEquals(Arrays.asList(O.ONE), grouped.get(O.ONE));
        }

        @Test
        public void canGroupFromAnIteratorUsingProvider() {
            final Iterator<O> values = Iterations.iterator(O.ONE);
            final Map<O, ArrayList<O>> grouped = Groups.groupBy(values, new Identity<O>(), LIST_FACTORY);
            Assert.assertEquals(Arrays.asList(O.ONE), grouped.get(O.ONE));
        }

        @Test
        public void canGroupFromAnArrayUsingProvider() {
            final O[] values = {O.ONE};
            final Map<O, ArrayList<O>> grouped = Groups.groupBy(values, new Identity<O>(), LIST_FACTORY);
            Assert.assertEquals(Arrays.asList(O.ONE), grouped.get(O.ONE));
        }

        @Test
        public void canGroupFromAnIterableUsingProviders() {
            final Iterable<O> values = Iterations.iterable(O.ONE);
            final Map<O, ArrayList<O>> grouped = Groups.groupBy(values, new Identity<O>(), LIST_FACTORY, MAP_FACTORY);
            Assert.assertEquals(Arrays.asList(O.ONE), grouped.get(O.ONE));
        }

        @Test
        public void canGroupFromAnIteratorUsingProviders() {
            final Iterator<O> values = Iterations.iterator(O.ONE);
            final Map<O, ArrayList<O>> grouped = Groups.groupBy(values, new Identity<O>(), LIST_FACTORY, MAP_FACTORY);
            Assert.assertEquals(Arrays.asList(O.ONE), grouped.get(O.ONE));
        }

        @Test
        public void canGroupFromAnArrayUsingProviders() {
            final O[] values = {O.ONE};
            final Map<O, ArrayList<O>> grouped = Groups.groupBy(values, new Identity<O>(), LIST_FACTORY, MAP_FACTORY);
            Assert.assertEquals(Arrays.asList(O.ONE), grouped.get(O.ONE));
        }

        @Test(expected = IllegalArgumentException.class)
        public void groupingANullIterableYieldsException() {
            final Iterable<O> values = null;
            Groups.groupBy(values, new Identity<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void groupingANullIterableUsingProviderYieldsException() {
            final Iterable<O> values = null;
            Groups.groupBy(values, new Identity<O>(), LIST_FACTORY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void groupingANullIterableUsingProvidersYieldsException() {
            final Iterable<O> values = null;
            Groups.groupBy(values, new Identity<O>(), LIST_FACTORY, MAP_FACTORY);
        }
    }

    public static class PartitionByTest {

        @Test
        public void canPartitionFromAnIterable() {
            final Iterable<O> values = Iterations.iterable(O.ONE);
            final Pair<List<O>, List<O>> partitioned = Groups.partition(values, new Always<O>());
            Assert.assertEquals(Arrays.asList(O.ONE), partitioned.first());
        }

        @Test
        public void canPartitionFromAnIterator() {
            final Iterator<O> values = Iterations.iterator(O.ONE);
            final Pair<List<O>, List<O>> partitioned = Groups.partition(values, new Always<O>());
            Assert.assertEquals(Arrays.asList(O.ONE), partitioned.first());
        }

        @Test
        public void canPartitionFromAnArray() {
            final O[] values = {O.ONE};
            final Pair<List<O>, List<O>> partitioned = Groups.partition(values, new Always<O>());
            Assert.assertEquals(Arrays.asList(O.ONE), partitioned.first());
        }

        @Test
        public void canPartitionFromAnIterableUsingProvider() {
            final Iterable<O> values = Iterations.iterable(O.ONE);
            final Pair<ArrayList<O>, ArrayList<O>> partitioned = Groups.partition(values, new Always<O>(), LIST_FACTORY);
            Assert.assertEquals(Arrays.asList(O.ONE), partitioned.first());
        }

        @Test
        public void canPartitionFromAnIteratorUsingProvider() {
            final Iterator<O> values = Iterations.iterator(O.ONE);
            final Pair<ArrayList<O>, ArrayList<O>> partitioned = Groups.partition(values, new Always<O>(), LIST_FACTORY);
            Assert.assertEquals(Arrays.asList(O.ONE), partitioned.first());
        }

        @Test
        public void canPartitionFromAnArrayUsingProvider() {
            final O[] values = {O.ONE};
            final Pair<ArrayList<O>, ArrayList<O>> partitioned = Groups.partition(values, new Always<O>(), LIST_FACTORY);
            Assert.assertEquals(Arrays.asList(O.ONE), partitioned.first());
        }

        @Test
        public void canPartitionFromAnIterableUsingProviders() {
            final Iterable<O> values = Iterations.iterable(O.ONE);
            final Pair<ArrayList<O>, HashSet<O>> partitioned = Groups.partition(values, new Always<O>(), LIST_FACTORY, SET_FACTORY);
            Assert.assertEquals(Arrays.asList(O.ONE), partitioned.first());
        }

        @Test
        public void canPartitionFromAnIteratorUsingProviders() {
            final Iterator<O> values = Iterations.iterator(O.ONE);
            final Pair<ArrayList<O>, HashSet<O>> partitioned = Groups.partition(values, new Always<O>(), LIST_FACTORY, SET_FACTORY);
            Assert.assertEquals(Arrays.asList(O.ONE), partitioned.first());
        }

        @Test
        public void canPartitionFromAnArrayUsingProviders() {
            final O[] values = {O.ONE};
            final Pair<ArrayList<O>, HashSet<O>> partitioned = Groups.partition(values, new Always<O>(), LIST_FACTORY, SET_FACTORY);
            Assert.assertEquals(Arrays.asList(O.ONE), partitioned.first());
        }

        @Test(expected = IllegalArgumentException.class)
        public void partitioningANullIterableYieldsException() {
            final Iterable<O> values = null;
            Groups.partition(values, new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void partitioningANullIterableUsingProviderYieldsException() {
            final Iterable<O> values = null;
            Groups.partition(values, new Always<O>(), LIST_FACTORY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void partitioningANullIterableUsingProvidersYieldsException() {
            final Iterable<O> values = null;
            Groups.partition(values, new Always<O>(), LIST_FACTORY, LIST_FACTORY);
        }
    }

    public static class IndexByTest {

        @Test(expected = IllegalArgumentException.class)
        public void indexByWithNullIteratorYieldsException() {
            final Function<O, O> delegate = new Identity<O>();
            final Provider<HashMap<O, O>> mapProvider = new HashMapFactory<O, O>();
            final Iterator<O> nullIterator = null;
            Groups.indexBy(nullIterator, delegate, mapProvider);
        }

        @Test(expected = IllegalArgumentException.class)
        public void indexByUsingProviderWithNullIterableYieldsException() {
            final Function<O, O> delegate = new Identity<O>();
            final Provider<HashMap<O, O>> mapProvider = new HashMapFactory<O, O>();
            final Iterable<O> nullIterable = null;
            Groups.indexBy(nullIterable, delegate, mapProvider);
        }

        @Test(expected = IllegalArgumentException.class)
        public void indexByWithNullIterableYieldsException() {
            final Function<O, O> delegate = new Identity<O>();
            final Iterable<O> nullIterable = null;
            Groups.indexBy(nullIterable, delegate);
        }

        @Test(expected = IllegalArgumentException.class)
        public void indexByWithNullDelegateYieldsException() {
            final Iterator<O> iterator = Iterations.iterator();
            final Provider<HashMap<O, O>> mapProvider = new HashMapFactory<O, O>();
            Groups.indexBy(iterator, null, mapProvider);
        }

        @Test(expected = IllegalArgumentException.class)
        public void indexByWithNullProvideYieldsException() {
            final Iterator<O> iterator = Iterations.iterator();
            final Function<O, O> delegate = new Identity<O>();
            final Provider<HashMap<O, O>> nullProvider = null;
            Groups.indexBy(iterator, delegate, nullProvider);
        }

        @Test
        public void everyElementFromIteratorIsIndexed() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
            final Function<O, O> delegate = new Identity<O>();
            final Provider<LinkedHashMap<O, O>> provider = new LinkedHashMapFactory<O, O>();
            final Map<O, O> indexed = Groups.indexBy(iterator, delegate, provider);
            Assert.assertEquals(Arrays.asList(O.ONE, O.ANOTHER), new ArrayList<O>(indexed.values()));
        }

        @Test
        public void canIndexUsingIterable() {
            final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER);
            final Function<O, O> delegate = new Identity<O>();
            final Map<O, O> indexed = Groups.indexBy(iterable, delegate);
            Assert.assertNotNull(indexed);
        }

        @Test
        public void canIndexUsingIterator() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
            final Function<O, O> delegate = new Identity<O>();
            final Map<O, O> indexed = Groups.indexBy(iterator, delegate);
            Assert.assertNotNull(indexed);
        }

        @Test
        public void canIndexUsingArray() {
            final O[] array = {O.ONE, O.ANOTHER};
            final Function<O, O> delegate = new Identity<O>();
            final Map<O, O> indexed = Groups.indexBy(array, delegate);
            Assert.assertNotNull(indexed);
        }

        @Test
        public void canIndexUsingIterableAndProvider() {
            final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER);
            final Function<O, O> delegate = new Identity<O>();
            final Provider<LinkedHashMap<O, O>> provider = new LinkedHashMapFactory<O, O>();
            final Map<O, O> indexed = Groups.indexBy(iterable, delegate, provider);
            Assert.assertNotNull(indexed);
        }

        @Test
        public void canIndexUsingIteratorAndProvider() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
            final Function<O, O> delegate = new Identity<O>();
            final Provider<LinkedHashMap<O, O>> provider = new LinkedHashMapFactory<O, O>();
            final Map<O, O> indexed = Groups.indexBy(iterator, delegate, provider);
            Assert.assertNotNull(indexed);
        }

        @Test
        public void canIndexUsingArrayAndProvider() {
            final O[] array = {O.ONE, O.ANOTHER};
            final Function<O, O> delegate = new Identity<O>();
            final Provider<LinkedHashMap<O, O>> provider = new LinkedHashMapFactory<O, O>();
            final Map<O, O> indexed = Groups.indexBy(array, delegate, provider);
            Assert.assertNotNull(indexed);
        }
    }

    public static class FacadeTest {

        @Test
        public void facadeIsNotFinal() {
            new Groups() {
            };
        }
    }
}
