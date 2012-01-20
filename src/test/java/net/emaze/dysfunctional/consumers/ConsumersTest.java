package net.emaze.dysfunctional.consumers;

import net.emaze.dysfunctional.output.OutputIterator;
import net.emaze.dysfunctional.output.StringOutputIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.Consumers;
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
    ConsumersTest.RefactorMe.class
})
public class ConsumersTest {

    private static List<Integer> list = Arrays.asList(1, 2);
    private static Integer[] array = new Integer[]{1, 2};

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
            final CollectionProvider<List<Integer>, Integer> collectionProvider = null;
            Consumers.all(list, collectionProvider);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAllWithNullIterableAndACollectionProvider() {
            final Iterable<Integer> iterable = null;
            Consumers.all(iterable, new CollectionProvider<List<Integer>, Integer>(list));
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

    public static class RefactorMe {

        private static Integer[] SINGLE_ELEMENT_ARRAY = {1};

        @Test
        public void searchOneInSingleValueArrayWNoPredicateYieldsJustFirst() {
            Maybe<Integer> got = Consumers.searchOne(SINGLE_ELEMENT_ARRAY);
            Assert.assertEquals(Maybe.just(1), got);
        }

        @Test
        public void searchOneWithEmptyIteratorYieldsNothing() {
            Assert.assertEquals(Maybe.nothing(), Consumers.searchOne(Collections.emptyList().iterator()));
        }

        @Test(expected = IllegalStateException.class)
        public void searchOneWithMultipleValuesIteratorThrows() {
            Consumers.searchOne(Arrays.asList(1, 2).iterator());
        }

        @Test
        public void searchOneWithSingleValueIteratorYieldsJustValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.searchOne(Arrays.asList(1).iterator()));
        }

        @Test
        public void searchOneWithSingleValueIterableYieldsJustValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.searchOne(Arrays.asList(1)));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallMaybeOneWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.searchOne(iterable);
        }

        @Test
        public void searchFirstWithEmptyIteratorYieldsNothing() {
            Assert.assertEquals(Maybe.nothing(), Consumers.search(Collections.emptyList().iterator()));
        }

        @Test
        public void searchFirstWithNonEmptyIteratorYieldsJustTheFirst() {
            Assert.assertEquals(Maybe.just(1), Consumers.search(Arrays.asList(1, 2).iterator()));
        }

        @Test
        public void searchFirstWithNonEmptyIterableYieldsJustTheFirst() {
            Assert.assertEquals(Maybe.just(1), Consumers.search(Arrays.asList(1, 2)));
        }

        @Test
        public void searchFirstWithNonEmptyArrayYieldsJustTheFirst() {
            Assert.assertEquals(Maybe.just(1), Consumers.search(new Integer[]{1, 2}));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallMaybeFirstWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.search(iterable);
        }

        @Test
        public void searchLastWithEmptyIteratorYieldsNothing() {
            Assert.assertEquals(Maybe.nothing(), Consumers.searchLast(Collections.emptyList().iterator()));
        }

        @Test
        public void searchLastWithMultipleValuesIteratorYieldsTheLastElement() {
            Assert.assertEquals(Maybe.just(2), Consumers.searchLast(Arrays.asList(1, 2).iterator()));
        }

        @Test
        public void searchLastWithSingleValueIteratorYieldsJustLastValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.searchLast(Arrays.asList(1).iterator()));
        }

        @Test
        public void searchLastWithSingleValueIterableYieldsJustLastValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.searchLast(Arrays.asList(1)));
        }

        @Test
        public void searchLastWithSingleValueArrayYieldsJustLastValue() {
            Assert.assertEquals(Maybe.just(1), Consumers.searchLast(new Integer[]{1}));
        }

        @Test(expected = IllegalArgumentException.class)
        public void callingMaybeLastWithNullIteratorYieldException() {
            Iterator<Object> aNullIter = null;
            Consumers.searchLast(aNullIter);
        }

        @Test(expected = IllegalArgumentException.class)
        public void callingMaybeLastWithNullIterableYieldException() {
            Iterable<Object> aNullIterable = null;
            Consumers.searchLast(aNullIterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void callingMaybeLastWithNullArrayYieldException() {
            Object[] aNullArray = null;
            Consumers.searchLast(aNullArray);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallMaybeLastWithNullIterable() {
            final Iterable<Object> iterable = null;
            Consumers.searchLast(iterable);
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
