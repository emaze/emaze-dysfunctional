package net.emaze.dysfunctional;

import net.emaze.dysfunctional.output.OutputIterator;
import net.emaze.dysfunctional.output.StringOutputIterator;
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
    ConsumersTest.All.class,
    ConsumersTest.Pipe.class,
    ConsumersTest.Facade.class,
    ConsumersTest.MaybeFirst.class,
    ConsumersTest.MaybeLast.class,
    ConsumersTest.MaybeOne.class
})
public class ConsumersTest {

    private static List<Integer> LIST = Arrays.asList(1, 2);
    private static Integer[] ARRAY = new Integer[]{1, 2};

    public static class All {

        @Test
        public void canFetchAllFromIterator() {
            List<Integer> got = Consumers.all(LIST.iterator());
            Assert.assertEquals(LIST, got);
        }

        @Test
        public void canFetchAllFromIterable() {
            List<Integer> got = Consumers.all(LIST);
            Assert.assertEquals(LIST, got);
        }

        @Test
        public void canFetchAllFromArray() {
            List<Integer> got = Consumers.all(ARRAY);
            Assert.assertEquals(LIST, got);
        }

        @Test
        public void canFetchAllFromIteratorWithCollection() {
            List<Integer> got = Consumers.all(LIST.iterator(), new ArrayList<Integer>());
            Assert.assertEquals(LIST, got);
        }

        @Test
        public void canFetchAllFromIterableWithCollection() {
            List<Integer> got = Consumers.all(LIST, new ArrayList<Integer>());
            Assert.assertEquals(LIST, got);
        }

        @Test
        public void canFetchAllFromArrayWithCollection() {
            List<Integer> got = Consumers.all(ARRAY, new ArrayList<Integer>());
            Assert.assertEquals(LIST, got);
        }

        @Test
        public void canFetchAllFromIteratorWithFactory() {
            List<Integer> got = Consumers.all(LIST.iterator(), new ArrayListFactory<Integer>());
            Assert.assertEquals(LIST, got);
        }

        @Test
        public void canFetchAllFromIterableWithFactory() {
            List<Integer> got = Consumers.all(LIST, new ArrayListFactory<Integer>());
            Assert.assertEquals(LIST, got);
        }

        @Test
        public void canFetchAllFromArrayWithFactory() {
            List<Integer> got = Consumers.all(ARRAY, new ArrayListFactory<Integer>());
            Assert.assertEquals(LIST, got);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.all(iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullCollection() {
            final Collection<Integer> collection = null;
            Consumers.all(LIST, collection);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithANullIterableAndACollection() {
            final Iterable<Integer> iterable = null;
            Consumers.all(iterable, LIST);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullCollectionProvider() {
            final CollectionProvider<List<Integer>, Integer> collectionProvider = null;
            Consumers.all(LIST, collectionProvider);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullIterableAndACollectionProvider() {
            final Iterable<Integer> iterable = null;
            Consumers.all(iterable, new CollectionProvider<List<Integer>, Integer>(LIST));
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

    public static class MaybeOne {

        private static Integer[] SINGLE_ELEMENT_ARRAY = {1};

        @Test
        public void maybeOneInSingleValueArrayWNoPredicateYieldsJustFirst() {
            Maybe<Integer> got = Consumers.maybeOne(SINGLE_ELEMENT_ARRAY);
            Assert.assertEquals(Maybe.just(1), got);
        }

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

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallMaybeOneWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.maybeOne(iterable);
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

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Consumers() {
            };
        }
    }
}
