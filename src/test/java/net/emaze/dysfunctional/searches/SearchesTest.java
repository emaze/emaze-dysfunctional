package net.emaze.dysfunctional.searches;

import java.util.Arrays;
import java.util.Collections;
import net.emaze.dysfunctional.logic.Always;
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
    SearchesTest.Search.class,
    SearchesTest.Find.class,
    SearchesTest.SearchOne.class,
    SearchesTest.FindOne.class
})
public class SearchesTest {

    private static Integer[] singleValueArray = new Integer[]{1};
    private static Integer[] multiValueArray = new Integer[]{1, 2};
    private static Iterable<Integer> singleValueIterable = Arrays.asList(1);
    private static Iterable<Integer> multiValueIterable = Arrays.asList(1, 2);
    private static Iterable<Object> emptyIterable = Collections.emptyList();

    public static class Search {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallFindWithANullIterable() {
            final Iterable<Object> iterable = null;
            Searches.search(iterable, new Always<Object>());
        }

        @Test
        public void searchInEmptyIteratorYieldsNothing() {
            Maybe<Object> got = Searches.search(emptyIterable.iterator(), new Always<Object>());
            Assert.assertEquals(Maybe.nothing(), got);
        }

        @Test
        public void searchInNonEmptyIteratorYieldsJustFirst() {
            Maybe<Integer> got = Searches.search(multiValueIterable.iterator(), new Always<Integer>());
            Assert.assertEquals(Maybe.just(1), got);
        }

        @Test
        public void searchInNonEmptyIterableYieldsJustFirst() {
            Maybe<Integer> got = Searches.search(multiValueIterable, new Always<Integer>());
            Assert.assertEquals(Maybe.just(1), got);
        }

        @Test
        public void searchInNonEmptyArrayYieldsJustFirst() {
            Maybe<Integer> got = Searches.search(multiValueArray, new Always<Integer>());
            Assert.assertEquals(Maybe.just(1), got);
        }
    }

    public static class Find {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallFindWithANullIterable() {
            final Iterable<Object> iterable = null;
            Searches.find(iterable, new Always<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void findInEmptyIteratorYieldsException() {
            Searches.find(emptyIterable.iterator(), new Always<Object>());
        }

        @Test
        public void findInNonEmptyIteratorYieldsJustFirst() {
            int got = Searches.find(multiValueIterable.iterator(), new Always<Integer>());
            Assert.assertEquals(1, got);
        }

        @Test
        public void findInNonEmptyIterableYieldsJustFirst() {
            int got = Searches.find(multiValueIterable, new Always<Integer>());
            Assert.assertEquals(1, got);
        }

        @Test
        public void findInNonEmptyArrayYieldsJustFirst() {
            int got = Searches.find(multiValueArray, new Always<Integer>());
            Assert.assertEquals(1, got);
        }
    }

    public static class SearchOne {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallSearchOneWithANullIterable() {
            final Iterable<Object> iterable = null;
            Searches.searchOne(iterable, new Always<Object>());
        }

        @Test
        public void searchOneInEmptyIteratorYieldsNothing() {
            Maybe<Object> got = Searches.searchOne(emptyIterable.iterator(), new Always<Object>());
            Assert.assertEquals(Maybe.nothing(), got);
        }

        @Test(expected = IllegalStateException.class)
        public void searchOneYieldsExceptionIfMoreThanOneElementIsFound() {
            Searches.searchOne(multiValueIterable.iterator(), new Always<Integer>());
        }

        @Test
        public void searchOneInSingleValueIteratorYieldsJustFirst() {
            Maybe<Integer> got = Searches.searchOne(singleValueIterable.iterator(), new Always<Integer>());
            Assert.assertEquals(Maybe.just(1), got);
        }

        @Test
        public void searchOneInSingleValueIterableYieldsJustFirst() {
            Maybe<Integer> got = Searches.searchOne(singleValueIterable, new Always<Integer>());
            Assert.assertEquals(Maybe.just(1), got);
        }

        @Test
        public void searchOneInSingleValueArrayYieldsJustFirst() {
            Maybe<Integer> got = Searches.searchOne(singleValueArray, new Always<Integer>());
            Assert.assertEquals(Maybe.just(1), got);
        }
    }

    public static class FindOne {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallFindOneWithANullIterable() {
            final Iterable<Object> iterable = null;
            Searches.findOne(iterable, new Always<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void findOneInEmptyIteratorYieldsException() {
            Searches.findOne(emptyIterable.iterator(), new Always<Object>());
        }

        @Test(expected = IllegalStateException.class)
        public void findOneYieldsExceptionIfMoreThanOneElementIsFound() {
            Searches.findOne(multiValueIterable.iterator(), new Always<Integer>());
        }

        @Test
        public void findOneInSingleValueIteratorYieldsJustFirst() {
            int got = Searches.findOne(singleValueIterable.iterator(), new Always<Integer>());
            Assert.assertEquals(1, got);
        }

        @Test
        public void findOneInSingleValueIterableYieldsJustFirst() {
            int got = Searches.findOne(singleValueIterable, new Always<Integer>());
            Assert.assertEquals(1, got);
        }

        @Test
        public void findOneInSingleValueArrayYieldsJustFirst() {
            int got = Searches.findOne(singleValueArray, new Always<Integer>());
            Assert.assertEquals(1, got);
        }
    }
}
