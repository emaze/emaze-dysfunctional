package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import java.util.Optional;
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
    SearchesTest.SearchFirst.class,
    SearchesTest.FindFirst.class,
    SearchesTest.SearchOne.class,
    SearchesTest.FindOne.class,
    SearchesTest.SearchLast.class,
    SearchesTest.FindLast.class,
    SearchesTest.Facade.class
})
public class SearchesTest {

    private static Integer[] singleValueArray = new Integer[]{1};
    private static Integer[] multiValueArray = new Integer[]{1, 2};
    private static Iterable<Integer> singleValueIterable = Arrays.asList(1);
    private static Iterable<Integer> multiValueIterable = Arrays.asList(1, 2);
    private static Iterable<Object> emptyIterable = Collections.emptyList();

    public static class Search {

        @Test(expected = IllegalArgumentException.class)
        public void searchingNullIterableYieldsException() {
            final Iterable<Integer> iterable = null;
            Searches.search(iterable, new Always<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void searchingNullIterableWithCollectionYieldsException() {
            final Iterable<Integer> iterable = null;
            Searches.search(iterable, new ArrayList<Integer>(), new Always<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void searchingNullIterableWithProviderYieldsException() {
            final Iterable<Integer> iterable = null;
            Searches.search(iterable, new ArrayListFactory<Integer>(), new Always<Integer>());
        }

        @Test
        public void yieldsAllMatchingFromIterable() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            final List<Integer> got = Searches.search(iterable, new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromIterableWithCollection() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            final List<Integer> got = Searches.search(iterable, new ArrayList<Integer>(), new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromIterableWithProvider() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            final List<Integer> got = Searches.search(iterable, new ArrayListFactory<Integer>(), new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromIterator() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            final List<Integer> got = Searches.search(iterator, new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromIteratorWithCollection() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            final List<Integer> got = Searches.search(iterator, new ArrayList<Integer>(), new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromIteratorWithProvider() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            final List<Integer> got = Searches.search(iterator, new ArrayListFactory<Integer>(), new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromArray() {
            final Integer[] array = new Integer[]{1, 2};
            final List<Integer> got = Searches.search(array, new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromArrayWithCollection() {
            final Integer[] array = new Integer[]{1, 2};
            final List<Integer> got = Searches.search(array, new ArrayList<Integer>(), new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromArrayWithProvider() {
            final Integer[] array = new Integer[]{1, 2};
            final List<Integer> got = Searches.search(array, new ArrayListFactory<Integer>(), new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void emptyMatchYieldsEmptyCollectionFromIterableWithCollection() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            final List<Integer> got = Searches.search(iterable, new ArrayList<Integer>(), new Never<Integer>());
            Assert.assertEquals(Arrays.asList(), got);
        }

        @Test
        public void emptyMatchYieldsEmptyCollectionFromIterableWithProvider() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            final List<Integer> got = Searches.search(iterable, new ArrayListFactory<Integer>(), new Never<Integer>());
            Assert.assertEquals(Arrays.asList(), got);
        }

        @Test
        public void emptyMatchYieldsEmptyCollectionFromIterator() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            final List<Integer> got = Searches.search(iterator, new Never<Integer>());
            Assert.assertEquals(Arrays.asList(), got);
        }

        @Test
        public void emptyMatchYieldsEmptyCollectionFromIteratorWithCollection() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            final List<Integer> got = Searches.search(iterator, new ArrayList<Integer>(), new Never<Integer>());
            Assert.assertEquals(Arrays.asList(), got);
        }

        @Test
        public void emptyMatchYieldsEmptyCollectionFromIteratorWithProvider() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            final List<Integer> got = Searches.search(iterator, new ArrayListFactory<Integer>(), new Never<Integer>());
            Assert.assertEquals(Arrays.asList(), got);
        }

        @Test
        public void emptyMatchYieldsEmptyCollectionFromArray() {
            final Integer[] array = new Integer[]{1, 2};
            final List<Integer> got = Searches.search(array, new Never<Integer>());
            Assert.assertEquals(Arrays.asList(), got);
        }

        @Test
        public void emptyMatchYieldsEmptyCollectionFromArrayWithCollection() {
            final Integer[] array = new Integer[]{1, 2};
            final List<Integer> got = Searches.search(array, new ArrayList<Integer>(), new Never<Integer>());
            Assert.assertEquals(Arrays.asList(), got);
        }

        @Test
        public void emptyMatchYieldsEmptyCollectionFromArrayWithProvider() {
            final Integer[] array = new Integer[]{1, 2};
            final List<Integer> got = Searches.search(array, new ArrayListFactory<Integer>(), new Never<Integer>());
            Assert.assertEquals(Arrays.asList(), got);
        }
    }

    public static class Find {

        @Test(expected = IllegalArgumentException.class)
        public void searchingNullIterableYieldsException() {
            final Iterable<Integer> iterable = null;
            Searches.find(iterable, new Always<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void searchingNullIterableWithCollectionYieldsException() {
            final Iterable<Integer> iterable = null;
            Searches.find(iterable, new ArrayList<Integer>(), new Always<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void searchingNullIterableWithProviderYieldsException() {
            final Iterable<Integer> iterable = null;
            Searches.find(iterable, new ArrayListFactory<Integer>(), new Always<Integer>());
        }

        @Test
        public void yieldsAllMatchingFromIterable() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            final List<Integer> got = Searches.find(iterable, new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromIterableWithCollection() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            final List<Integer> got = Searches.find(iterable, new ArrayList<Integer>(), new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromIterableWithProvider() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            final List<Integer> got = Searches.find(iterable, new ArrayListFactory<Integer>(), new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromIterator() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            final List<Integer> got = Searches.find(iterator, new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromIteratorWithCollection() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            final List<Integer> got = Searches.find(iterator, new ArrayList<Integer>(), new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromIteratorWithProvider() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            final List<Integer> got = Searches.find(iterator, new ArrayListFactory<Integer>(), new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromArray() {
            final Integer[] array = new Integer[]{1, 2};
            final List<Integer> got = Searches.find(array, new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromArrayWithCollection() {
            final Integer[] array = new Integer[]{1, 2};
            final List<Integer> got = Searches.find(array, new ArrayList<Integer>(), new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test
        public void yieldsAllMatchingFromArrayWithProvider() {
            final Integer[] array = new Integer[]{1, 2};
            final List<Integer> got = Searches.find(array, new ArrayListFactory<Integer>(), new Always<Integer>());
            Assert.assertEquals(Arrays.asList(1, 2), got);
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyMatchYieldsExceptionFromIterable() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            Searches.find(iterable, new Never<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyMatchYieldsExceptionFromIterableWithCollection() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            Searches.find(iterable, new ArrayList<Integer>(), new Never<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyMatchYieldsExceptionFromIterableWithProvider() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            Searches.find(iterable, new ArrayListFactory<Integer>(), new Never<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyMatchYieldsExceptionFromIterator() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            Searches.find(iterator, new Never<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyMatchYieldsExceptionFromIteratorWithCollection() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            Searches.find(iterator, new ArrayList<Integer>(), new Never<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyMatchYieldsExceptionFromIteratorWithProvider() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            Searches.find(iterator, new ArrayListFactory<Integer>(), new Never<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyMatchYieldsExceptionFromArray() {
            final Integer[] array = new Integer[]{1, 2};
            Searches.find(array, new Never<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyMatchYieldsExceptionFromArrayWithCollection() {
            final Integer[] array = new Integer[]{1, 2};
            Searches.find(array, new ArrayList<Integer>(), new Never<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyMatchYieldsExceptionFromArrayWithProvider() {
            final Integer[] array = new Integer[]{1, 2};
            Searches.find(array, new ArrayListFactory<Integer>(), new Never<Integer>());
        }
    }

    public static class SearchFirst {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallFindWithANullIterable() {
            final Iterable<Object> iterable = null;
            Searches.searchFirst(iterable, new Always<Object>());
        }

        @Test
        public void searchInEmptyIteratorYieldsNothing() {
            Optional<Object> got = Searches.searchFirst(emptyIterable.iterator(), new Always<Object>());
            Assert.assertEquals(Optional.empty(), got);
        }

        @Test
        public void searchInNonEmptyIteratorYieldsJustFirst() {
            Optional<Integer> got = Searches.searchFirst(multiValueIterable.iterator(), new Always<Integer>());
            Assert.assertEquals(Optional.of(1), got);
        }

        @Test
        public void searchInNonEmptyIterableYieldsJustFirst() {
            Optional<Integer> got = Searches.searchFirst(multiValueIterable, new Always<Integer>());
            Assert.assertEquals(Optional.of(1), got);
        }

        @Test
        public void searchInNonEmptyArrayYieldsJustFirst() {
            Optional<Integer> got = Searches.searchFirst(multiValueArray, new Always<Integer>());
            Assert.assertEquals(Optional.of(1), got);
        }
    }

    public static class FindFirst {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallFindWithANullIterable() {
            final Iterable<Object> iterable = null;
            Searches.findFirst(iterable, new Always<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void findInEmptyIteratorYieldsException() {
            Searches.findFirst(emptyIterable.iterator(), new Always<Object>());
        }

        @Test
        public void findInNonEmptyIteratorYieldsJustFirst() {
            int got = Searches.findFirst(multiValueIterable.iterator(), new Always<Integer>());
            Assert.assertEquals(1, got);
        }

        @Test
        public void findInNonEmptyIterableYieldsJustFirst() {
            int got = Searches.findFirst(multiValueIterable, new Always<Integer>());
            Assert.assertEquals(1, got);
        }

        @Test
        public void findInNonEmptyArrayYieldsJustFirst() {
            int got = Searches.findFirst(multiValueArray, new Always<Integer>());
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
            Optional<Object> got = Searches.searchOne(emptyIterable.iterator(), new Always<Object>());
            Assert.assertEquals(Optional.empty(), got);
        }

        @Test(expected = IllegalStateException.class)
        public void searchOneYieldsExceptionIfMoreThanOneElementIsFound() {
            Searches.searchOne(multiValueIterable.iterator(), new Always<Integer>());
        }

        @Test
        public void searchOneInSingleValueIteratorYieldsJustFirst() {
            Optional<Integer> got = Searches.searchOne(singleValueIterable.iterator(), new Always<Integer>());
            Assert.assertEquals(Optional.of(1), got);
        }

        @Test
        public void searchOneInSingleValueIterableYieldsJustFirst() {
            Optional<Integer> got = Searches.searchOne(singleValueIterable, new Always<Integer>());
            Assert.assertEquals(Optional.of(1), got);
        }

        @Test
        public void searchOneInSingleValueArrayYieldsJustFirst() {
            Optional<Integer> got = Searches.searchOne(singleValueArray, new Always<Integer>());
            Assert.assertEquals(Optional.of(1), got);
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

    public static class SearchLast {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallSearchLastWithANullIterable() {
            final Iterable<Object> iterable = null;
            Searches.searchLast(iterable, new Always<Object>());
        }

        @Test
        public void yieldsLastMatchingFromIterable() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            final Optional<Integer> got = Searches.searchLast(iterable, new Always<Integer>());
            Assert.assertEquals(Optional.of(2), got);
        }

        @Test
        public void yieldsLastMatchingFromIterator() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            final Optional<Integer> got = Searches.searchLast(iterator, new Always<Integer>());
            Assert.assertEquals(Optional.of(2), got);
        }

        @Test
        public void yieldsLastMatchingFromArray() {
            final Integer[] array = new Integer[]{1, 2};
            final Optional<Integer> got = Searches.searchLast(array, new Always<Integer>());
            Assert.assertEquals(Optional.of(2), got);
        }

        @Test
        public void yieldsNothingFromIterableWhenNothingMatches() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            final Optional<Integer> got = Searches.searchLast(iterable, new Never<Integer>());
            Assert.assertEquals(Optional.<Integer>empty(), got);
        }

        @Test
        public void yieldsNothingFromIteratorWhenNothingMatches() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            final Optional<Integer> got = Searches.searchLast(iterator, new Never<Integer>());
            Assert.assertEquals(Optional.<Integer>empty(), got);
        }

        @Test
        public void yieldsNothingFromArrayWhenNothingMatches() {
            final Integer[] array = new Integer[]{1, 2};
            final Optional<Integer> got = Searches.searchLast(array, new Never<Integer>());
            Assert.assertEquals(Optional.<Integer>empty(), got);
        }
    }

    public static class FindLast {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallFindLastWithANullIterable() {
            final Iterable<Object> iterable = null;
            Searches.findLast(iterable, new Always<Object>());
        }

        @Test
        public void yieldsLastMatchingFromIterable() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            final Integer got = Searches.findLast(iterable, new Always<Integer>());
            Assert.assertEquals(Integer.valueOf(2), got);
        }

        @Test
        public void yieldsLastMatchingFromIterator() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            final Integer got = Searches.findLast(iterator, new Always<Integer>());
            Assert.assertEquals(Integer.valueOf(2), got);
        }

        @Test
        public void yieldsLastMatchingFromArray() {
            final Integer[] array = new Integer[]{1, 2};
            final Integer got = Searches.findLast(array, new Always<Integer>());
            Assert.assertEquals(Integer.valueOf(2), got);
        }

        @Test(expected = IllegalArgumentException.class)
        public void throwsWithIterableWhenNothingMatches() {
            final Iterable<Integer> iterable = Iterations.iterable(1, 2);
            Searches.findLast(iterable, new Never<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void throwsWithIteratorWhenNothingMatches() {
            final Iterator<Integer> iterator = Iterations.iterator(1, 2);
            Searches.findLast(iterator, new Never<Integer>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void throwsWithArrayWhenNothingMatches() {
            final Integer[] array = new Integer[]{1, 2};
            Searches.findLast(array, new Never<Integer>());
        }
    }

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Searches() {
            };
        }
    }
}
