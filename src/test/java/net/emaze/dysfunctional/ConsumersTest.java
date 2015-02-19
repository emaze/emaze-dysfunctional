package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.collections.HashMapFactory;
import java.util.function.Supplier;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.output.OutputIterator;
import net.emaze.dysfunctional.output.StringOutputIterator;
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
    ConsumersTest.All.class,
    ConsumersTest.Dict.class,
    ConsumersTest.Pipe.class,
    ConsumersTest.Facade.class,
    ConsumersTest.First.class,
    ConsumersTest.MaybeFirst.class,
    ConsumersTest.Last.class,
    ConsumersTest.MaybeLast.class,
    ConsumersTest.One.class,
    ConsumersTest.MaybeOne.class,
    ConsumersTest.Nth.class,
    ConsumersTest.MaybeNth.class,
    ConsumersTest.At.class,
    ConsumersTest.MaybeAt.class
})
public class ConsumersTest {

    public static class All {

        @Test
        public void canFetchAllFromIterator() {
            final List<Integer> got = Consumers.all(Iterations.iterator(1, 2));
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void canFetchAllFromIterable() {
            final List<Integer> got = Consumers.all(Iterations.iterable(1, 2));
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void canFetchAllFromArray() {
            final List<Integer> got = Consumers.all(new Integer[]{1, 2});
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void canFetchAllFromIteratorWithCollection() {
            final List<Integer> got = Consumers.all(Iterations.iterator(1, 2), new ArrayList<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void canFetchAllFromIterableWithCollection() {
            final List<Integer> got = Consumers.all(Iterations.iterable(1, 2), new ArrayList<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void canFetchAllFromArrayWithCollection() {
            final List<Integer> got = Consumers.all(new Integer[]{1, 2}, new ArrayList<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void canFetchAllFromIteratorWithFactory() {
            final List<Integer> got = Consumers.all(Iterations.iterator(1, 2), new ArrayListFactory<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void canFetchAllFromIterableWithFactory() {
            final List<Integer> got = Consumers.all(Iterations.iterable(1, 2), new ArrayListFactory<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void canFetchAllFromArrayWithFactory() {
            final List<Integer> got = Consumers.all(new Integer[]{1, 2}, new ArrayListFactory<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.all(iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullCollection() {
            final Collection<Integer> collection = null;
            Consumers.all(Arrays.asList(1, 2), collection);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallForIteratorAllWithNullCollection() {
            final List<Integer> collection = null;
            Consumers.all(Arrays.asList(1, 2).iterator(), collection);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallForArrayAllWithNullCollection() {
            final List<Integer> collection = null;
            Consumers.all(new Integer[]{1, 2}, collection);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithANullIterableAndACollection() {
            final Iterable<Integer> iterable = null;
            Consumers.all(iterable, new ArrayList<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullCollectionProvider() {
            final Supplier<List<Integer>> collectionProvider = null;
            Consumers.all(Arrays.asList(1, 2), collectionProvider);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullIterableAndACollectionProvider() {
            final Iterable<Integer> iterable = null;
            Consumers.all(iterable, new ArrayListFactory<Integer>());
        }
    }

    public static class Dict {

        @Test
        public void canFetchDictFromIterator() {
            final Iterator<Pair<O, O>> iterator = Iterations.iterator(Pair.of(O.ONE, O.ONE));
            final Map<O, O> got = Consumers.dict(iterator);
            final Map<O, O> expected = Maps.<O, O>builder().add(O.ONE, O.ONE).toMap();
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canFetchDictFromIterable() {
            final Iterable<Pair<O, O>> iterable = Iterations.iterable(Pair.of(O.ONE, O.ONE));
            final Map<O, O> got = Consumers.dict(iterable);
            final Map<O, O> expected = Maps.<O, O>builder().add(O.ONE, O.ONE).toMap();
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canFetchDictFromArray() {
            final Map<O, O> got = Consumers.dict(Pair.of(O.ONE, O.ONE));
            final Map<O, O> expected = Maps.<O, O>builder().add(O.ONE, O.ONE).toMap();
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canFetchDictFromIteratorWithMap() {
            final Iterator<Pair<O, O>> iterator = Iterations.iterator(Pair.of(O.ONE, O.ONE));
            final HashMap<O, O> got = Consumers.dict(iterator, new HashMap<O, O>());
            final Map<O, O> expected = Maps.<O, O>builder().add(O.ONE, O.ONE).toMap();
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canFetchDictFromIterableWithMap() {
            final Iterable<Pair<O, O>> iterable = Iterations.iterable(Pair.of(O.ONE, O.ONE));
            final HashMap<O, O> got = Consumers.dict(iterable, new HashMap<O, O>());
            final Map<O, O> expected = Maps.<O, O>builder().add(O.ONE, O.ONE).toMap();
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canFetchDictFromArrayWithMap() {
            final Map<O, O> got = Consumers.dict(new HashMap<O, O>(), Pair.of(O.ONE, O.ONE));
            final Map<O, O> expected = Maps.<O, O>builder().add(O.ONE, O.ONE).toMap();
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canFetchDictFromIteratorWithFactory() {
            final Iterator<Pair<O, O>> iterator = Iterations.iterator(Pair.of(O.ONE, O.ONE));
            final HashMap<O, O> got = Consumers.dict(iterator, new HashMapFactory<O, O>());
            final Map<O, O> expected = Maps.<O, O>builder().add(O.ONE, O.ONE).toMap();
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canFetchDictFromIterableWithFactory() {
            final Iterable<Pair<O, O>> iterable = Iterations.iterable(Pair.of(O.ONE, O.ONE));
            final HashMap<O, O> got = Consumers.dict(iterable, new HashMapFactory<O, O>());
            final Map<O, O> expected = Maps.<O, O>builder().add(O.ONE, O.ONE).toMap();
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canFetchDictFromArrayWithFactory() {
            final HashMap<O, O> got = Consumers.dict(new HashMapFactory<O, O>(), Pair.of(O.ONE, O.ONE));
            final Map<O, O> expected = Maps.<O, O>builder().add(O.ONE, O.ONE).toMap();
            Assert.assertEquals(expected, got);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallDictWithNullIterable() {
            final Iterable<Pair<O, O>> iterable = null;
            Consumers.dict(iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallDictWithNullMap() {
            final Map<O, O> map = null;
            Iterable<Pair<O, O>> iterable = Iterations.iterable(Pair.of(O.ONE, O.ONE));
            Consumers.dict(iterable, map);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallDictForIteratorWithNullMap() {
            final Map<O, O> map = null;
            Iterator<Pair<O, O>> iterator = Iterations.iterator(Pair.of(O.ONE, O.ONE));
            Consumers.dict(iterator, map);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallDictForArrayWithNullMap() {
            final Map<O, O> map = null;
            Consumers.dict(map, Pair.of(O.ONE, O.ONE));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallDictWithANullIterableAndACollection() {
            final Iterable<Pair<O, O>> iterable = null;
            Consumers.dict(iterable, new HashMap<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallDictWithNullCollectionProvider() {
            final Supplier<Map<O, O>> collectionProvider = null;
            Iterable<Pair<O, O>> iterable = Iterations.iterable(Pair.of(O.ONE, O.ONE));
            Consumers.dict(iterable, collectionProvider);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallDictWithNullIterableAndACollectionProvider() {
            final Iterable<Pair<O, O>> iterable = null;
            Consumers.dict(iterable, new HashMapFactory<O, O>());
        }
    }

    public static class Pipe {

        @Test
        public void canPipeFromIterator() {
            final Iterator<String> in = Iterations.iterator("1", "2", "3");
            final StringOutputIterator output = new StringOutputIterator();
            Consumers.pipe(in, output);
            Assert.assertEquals("123", output.toString());
        }

        @Test
        public void canPipeFromIterable() {
            final Iterable<String> in = Iterations.iterable("1", "2", "3");
            final StringOutputIterator output = new StringOutputIterator();
            Consumers.pipe(in, output);
            Assert.assertEquals("123", output.toString());
        }

        @Test
        public void canPipeFromArray() {
            final String[] in = new String[]{"1", "2", "3"};
            final StringOutputIterator output = new StringOutputIterator();
            Consumers.pipe(in, output);
            Assert.assertEquals("123", output.toString());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallPipeWithNullIterable() {
            final Iterable<Object> iterable = null;
            final OutputIterator<Object> outputIterator = new StubOutputIterator();
            Consumers.pipe(iterable, outputIterator);
        }

        private static class StubOutputIterator implements OutputIterator<Object> {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public void next(Object element) {
            }
        }
    }

    public static class One {

        @Test
        public void oneWithSingleValueIteratorYieldsValue() {
            Assert.assertEquals(O.ONE, Consumers.one(Iterations.iterator(O.ONE)));
        }

        @Test
        public void oneWithSingleValueIterableYieldsValue() {
            Assert.assertEquals(O.ONE, Consumers.one(Iterations.iterable(O.ONE)));
        }

        @Test
        public void oneWithSingleValueArrayYieldsValue() {
            Assert.assertEquals(O.ONE, Consumers.one(new O[]{O.ONE}));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallOneWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.one(iterable);
        }
    }

    public static class MaybeOne {

        private static Integer[] SINGLE_ELEMENT_ARRAY = {1};

        @Test
        public void maybeOneInSingleValueArrayWNoPredicateYieldsJustFirst() {
            Maybe<Integer> got = Consumers.maybeOne(SINGLE_ELEMENT_ARRAY);
            Assert.assertEquals(Maybe.just(1), got);
        }

        @Test
        public void maybeOneWithEmptyIteratorYieldsNothing() {
            Assert.assertEquals(Maybe.nothing(), Consumers.maybeOne(Iterations.iterator()));
        }

        @Test(expected = IllegalStateException.class)
        public void maybeOneWithMultipleValuesIteratorThrows() {
            Consumers.maybeOne(Iterations.iterator(1, 2));
        }

        @Test
        public void maybeOneWithSingleValueIteratorYieldsJustValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeOne(Iterations.iterator(1)));
        }

        @Test
        public void maybeOneWithSingleValueIterableYieldsJustValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeOne(Iterations.iterable(1)));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallMaybeOneWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.maybeOne(iterable);
        }
    }

    public static class First {

        @Test(expected = IllegalArgumentException.class)
        public void firstWithEmptyIteratorYieldsException() {
            Consumers.first(Iterations.iterator());
        }

        @Test(expected = IllegalArgumentException.class)
        public void firstWithNullIterableYieldsException() {
            final Iterable<Object> iterable = null;
            Consumers.first(iterable);
        }

        @Test
        public void firstWithIteratorYieldsTheFirst() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
            Assert.assertEquals(O.ONE, Consumers.first(iterator));
        }

        @Test
        public void firstWithIterableYieldsTheFirst() {
            final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER);
            Assert.assertEquals(O.ONE, Consumers.first(iterable));
        }

        @Test
        public void firstWithArrayYieldsTheFirst() {
            final O[] array = {O.ONE, O.ANOTHER};
            Assert.assertEquals(O.ONE, Consumers.first(array));
        }
    }

    public static class MaybeFirst {

        @Test
        public void maybeFirstWithEmptyIteratorYieldsNothing() {
            Assert.assertEquals(Maybe.nothing(), Consumers.maybeFirst(Iterations.iterator()));
        }

        @Test
        public void maybeFirstWithNonEmptyIteratorYieldsJustTheFirst() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeFirst(Iterations.iterator(1, 2)));
        }

        @Test
        public void maybeFirstWithNonEmptyIterableYieldsJustTheFirst() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeFirst(Iterations.iterable(1, 2)));
        }

        @Test
        public void maybeFirstWithNonEmptyArrayYieldsJustTheFirst() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeFirst(new Integer[]{1, 2}));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallMaybeFirstWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.maybeFirst(iterable);
        }
    }

    public static class Last {

        @Test
        public void lastFromIterableYieldsTheLastElement() {
            final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER);
            Assert.assertEquals(O.ANOTHER, Consumers.last(iterable));
        }

        @Test
        public void lastFromIteratorYieldsTheLastElement() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
            Assert.assertEquals(O.ANOTHER, Consumers.last(iterator));
        }

        @Test
        public void lastFromArrayYieldsTheLastElement() {
            final O[] array = {O.ONE, O.ANOTHER};
            Assert.assertEquals(O.ANOTHER, Consumers.last(array));
        }

        @Test(expected = IllegalArgumentException.class)
        public void callingLastWithNullIterableYieldException() {
            Iterable<Object> aNullIterable = null;
            Consumers.last(aNullIterable);
        }
    }

    public static class MaybeLast {

        @Test
        public void maybeLastWithEmptyIteratorYieldsNothing() {
            Assert.assertEquals(Maybe.nothing(), Consumers.maybeLast(Iterations.iterator()));
        }

        @Test
        public void maybeLastWithMultipleValuesIteratorYieldsTheLastElement() {
            Assert.assertEquals(Maybe.just(2), Consumers.maybeLast(Iterations.iterator(1, 2)));
        }

        @Test
        public void maybeLastWithSingleValueIteratorYieldsJustLastValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeLast(Iterations.iterator(1)));
        }

        @Test
        public void maybeLastWithSingleValueIterableYieldsJustLastValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeLast(Iterations.iterable(1)));
        }

        @Test
        public void maybeLastWithSingleValueArrayYieldsJustLastValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeLast(new Integer[]{1}));
        }

        @Test(expected = IllegalArgumentException.class)
        public void callingMaybeLastWithNullIteratorYieldException() {
            final Iterator<Object> aNullIter = null;
            Consumers.maybeLast(aNullIter);
        }

        @Test(expected = IllegalArgumentException.class)
        public void callingMaybeLastWithNullIterableYieldException() {
            final Iterable<Object> aNullIterable = null;
            Consumers.maybeLast(aNullIterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void callingMaybeLastWithNullArrayYieldException() {
            final Object[] aNullArray = null;
            Consumers.maybeLast(aNullArray);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallMaybeLastWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.maybeLast(iterable);
        }
    }

    public static class Nth {

        @Test
        public void canFetchNthFromIterator() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.nth(1, Iterations.iterator(1, 2)));
        }

        @Test
        public void canFetchNthFromIterable() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.nth(1, Iterations.iterable(1, 2)));
        }

        @Test
        public void canFetchNthFromArray() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.nth(1, new Integer[]{1, 2}));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallNthWithANullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.nth(1, iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotFetchAtOnANullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.nth(1, iterable);
        }
    }

    public static class MaybeNth {

        @Test
        public void canFetchNthFromAnIterable() {
            final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER);
            Assert.assertEquals(Maybe.just(O.ONE), Consumers.maybeNth(1, iterable));
        }

        @Test
        public void canFetchNthFromAnIterator() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
            Assert.assertEquals(Maybe.just(O.ONE), Consumers.maybeNth(1, iterator));
        }

        @Test
        public void canFetchNthFromAnArray() {
            final O[] array = {O.ONE, O.ANOTHER};
            Assert.assertEquals(Maybe.just(O.ONE), Consumers.maybeNth(1, array));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotFetchAtOnANullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.maybeNth(1, iterable);
        }
    }

    public static class At {

        @Test
        public void canFetchAtFromIterator() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.at(0, Iterations.iterator(1, 2)));
        }

        @Test
        public void canFetchAtFromIterable() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.at(0, Iterations.iterable(1, 2)));
        }

        @Test
        public void canFetchAtFromArray() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.at(0, new Integer[]{1, 2}));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAtOnANullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.at(2, iterable);
        }
    }

    public static class MaybeAt {

        @Test
        public void canFetchAtFromIterator() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE);
            Assert.assertEquals(Maybe.just(O.ONE), Consumers.maybeAt(0, iterator));
        }

        @Test
        public void canFetchAtFromIterable() {
            final Iterable<O> iterable = Iterations.iterable(O.ONE);
            Assert.assertEquals(Maybe.just(O.ONE), Consumers.maybeAt(0, iterable));
        }

        @Test
        public void canFetchAtFromArray() {
            final O[] array = {O.ONE};
            Assert.assertEquals(Maybe.just(O.ONE), Consumers.maybeAt(0, array));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAtOnANullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.maybeAt(2, iterable);
        }
    }

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Consumers() {
            };
        }
    }
}
