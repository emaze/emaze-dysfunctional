package net.emaze.dysfunctional.consumers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.collections.CollectionProvider;
import net.emaze.dysfunctional.options.Maybe;
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
    ConsumersTest.First.class,
    ConsumersTest.One.class,
    ConsumersTest.All.class,
    ConsumersTest.Last.class,
    ConsumersTest.Pipe.class,
    ConsumersTest.MaybeFirst.class,
    ConsumersTest.MaybeOne.class,
    ConsumersTest.MaybeLast.class
})
public class ConsumersTest {

    private static List<Integer> list = Arrays.asList(1, 2);
    private static Integer[] array = new Integer[]{1, 2};

    public static class First {

        @Test
        public void canFetchFirstFromIterator() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.first(list.iterator()));
        }

        @Test
        public void canFetchFirstFromIterable() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.first(list));
        }

        @Test
        public void canFetchFirstFromArray() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.first(array));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallFirstWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.first(iterable);
        }
    }

    public static class One {

        @Test(expected = IllegalArgumentException.class)
        public void oneWithEmptyIteratorThrows() {
            Consumers.one(Collections.emptyList().iterator());
        }

        @Test(expected = IllegalStateException.class)
        public void oneWithMultipleValuesIteratorThrows() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.one(Arrays.asList(1, 2).iterator()));
        }

        @Test
        public void oneWithSingleValueIteratorYieldsValue() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.one(Arrays.asList(1).iterator()));
        }

        @Test
        public void oneWithSingleValueIterableYieldsValue() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.one(Arrays.asList(1)));
        }

        @Test
        public void oneWithSingleValueArrayYieldsValue() {
            Assert.assertEquals(Integer.valueOf(1), Consumers.one(new Integer[]{1}));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallOneWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.one(iterable);
        }
    }

    public static class All {

        @Test
        public void canFetchAllFromIterator() {
            List<Integer> got = Consumers.all(list.iterator());
            Assert.assertEquals(list, got);
        }

        @Test
        public void canFetchAllFromIterable() {
            List<Integer> got = Consumers.all(list);
            Assert.assertEquals(list, got);
        }

        @Test
        public void canFetchAllFromArray() {
            List<Integer> got = Consumers.all(array);
            Assert.assertEquals(list, got);
        }

        @Test
        public void canFetchAllFromIteratorWithCollection() {
            List<Integer> got = Consumers.all(list.iterator(), new ArrayList<Integer>());
            Assert.assertEquals(list, got);
        }

        @Test
        public void canFetchAllFromIterableWithCollection() {
            List<Integer> got = Consumers.all(list, new ArrayList<Integer>());
            Assert.assertEquals(list, got);
        }

        @Test
        public void canFetchAllFromArrayWithCollection() {
            List<Integer> got = Consumers.all(array, new ArrayList<Integer>());
            Assert.assertEquals(list, got);
        }

        @Test
        public void canFetchAllFromIteratorWithFactory() {
//            List<Integer> got = Consumers.<ArrayList<Integer>, Integer, ArrayListFactory<Integer>>all(list.iterator(), new ArrayListFactory<Integer>());
            List<Integer> got = Consumers.all(list.iterator(), new ArrayListFactory<Integer>());
            Assert.assertEquals(list, got);
        }

        @Test
        public void canFetchAllFromIterableWithFactory() {
            List<Integer> got = Consumers.all(list, new ArrayListFactory<Integer>());
            Assert.assertEquals(list, got);
        }

        @Test
        public void canFetchAllFromArrayWithFactory() {
            List<Integer> got = Consumers.all(array, new ArrayListFactory<Integer>());
            Assert.assertEquals(list, got);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.all(iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullCollection() {
            final Collection<Integer> collection = null;
            Consumers.all(list, collection);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithANullIterableAndACollection() {
            final Iterable<Integer> iterable = null;
            Consumers.all(iterable, list);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullCollectionProvider() {
            final CollectionProvider collectionProvider = null; // TODO: fix generics
            Consumers.all(list, collectionProvider);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullIterableAndACollectionProvider() { // TODO: fix generics
            final Iterable<Integer> iterable = null;
            Consumers.all(iterable, new CollectionProvider(list));
        }
    }

    public static class Last {

        @Test
        public void canFetchLastFromIterator() {
            Assert.assertEquals(Integer.valueOf(2), Consumers.last(list.iterator()));
        }

        @Test
        public void canFetchLastFromIterable() {
            Assert.assertEquals(Integer.valueOf(2), Consumers.last(list));
        }

        @Test
        public void canFetchLastFromArray() {
            Assert.assertEquals(Integer.valueOf(2), Consumers.last(array));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallLastWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.last(iterable);
        }
    }

    public static class Pipe {

        @Test
        public void canPipeFromIterator() {
            List<String> in = Arrays.asList("1", "2", "3");
            StringOutputIterator output = new StringOutputIterator();
            Consumers.pipe(in.iterator(), output);
            Assert.assertEquals("123", output.toString());
        }

        @Test
        public void canPipeFromIterable() {
            List<String> in = Arrays.asList("1", "2", "3");
            StringOutputIterator output = new StringOutputIterator();
            Consumers.pipe(in, output);
            Assert.assertEquals("123", output.toString());
        }

        @Test
        public void canPipeFromArray() {
            String[] in = new String[]{"1", "2", "3"};
            StringOutputIterator output = new StringOutputIterator();
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

    public static class MaybeFirst {

        @Test
        public void maybeFirstWithEmptyIteratorYieldsNothing() {
            Assert.assertEquals(Maybe.nothing(), Consumers.maybeFirst(Collections.emptyList().iterator()));
        }

        @Test
        public void maybeFirstWithNonEmptyIteratorYieldsJustTheFirst() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeFirst(Arrays.asList(1, 2).iterator()));
        }

        @Test
        public void maybeFirstWithNonEmptyIterableYieldsJustTheFirst() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeFirst(Arrays.asList(1, 2)));
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

    public static class MaybeOne {

        @Test
        public void maybeOneWithEmptyIteratorYieldsNothing() {
            Assert.assertEquals(Maybe.nothing(), Consumers.maybeOne(Collections.emptyList().iterator()));
        }

        @Test(expected = IllegalStateException.class)
        public void maybeOneWithMultipleValuesIteratorThrows() {
            Consumers.maybeOne(Arrays.asList(1, 2).iterator());
        }

        @Test
        public void maybeOneWithSingleValueIteratorYieldsJustValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeOne(Arrays.asList(1).iterator()));
        }

        @Test
        public void maybeOneWithSingleValueIterableYieldsJustValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeOne(Arrays.asList(1)));
        }

        @Test
        public void maybeOneWithSingleValueArrayYieldsJustValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeOne(new Integer[]{1}));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallMaybeOneWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.maybeOne(iterable);
        }
    }

    public static class MaybeLast {

        @Test
        public void maybeLastWithEmptyIteratorYieldsNothing() {
            Assert.assertEquals(Maybe.nothing(), Consumers.maybeLast(Collections.emptyList().iterator()));
        }

        @Test
        public void maybeLastWithMultipleValuesIteratorYieldsTheLastElement() {
            Assert.assertEquals(Maybe.just(2), Consumers.maybeLast(Arrays.asList(1, 2).iterator()));
        }

        @Test
        public void maybeLastWithSingleValueIteratorYieldsJustLastValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeLast(Arrays.asList(1).iterator()));
        }

        @Test
        public void maybeLastWithSingleValueIterableYieldsJustLastValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeLast(Arrays.asList(1)));
        }

        @Test
        public void maybeLastWithSingleValueArrayYieldsJustLastValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.maybeLast(new Integer[]{1}));
        }

        @Test(expected = IllegalArgumentException.class)
        public void callingMaybeLastWithNullIteratorYieldException() {
            Iterator<Object> aNullIter = null;
            Consumers.maybeLast(aNullIter);
        }

        @Test(expected = IllegalArgumentException.class)
        public void callingMaybeLastWithNullIterableYieldException() {
            Iterable<Object> aNullIterable = null;
            Consumers.maybeLast(aNullIterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void callingMaybeLastWithNullArrayYieldException() {
            Object[] aNullArray = null;
            Consumers.maybeLast(aNullArray);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallMaybeLastWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.maybeLast(iterable);
        }
    }
}
