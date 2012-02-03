package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    PaginationTest.Functions.class,
    PaginationTest.Degenerations.class,
    PaginationTest.Facade.class
})
public class PaginationTest {

    public static class Functions {

        @Test
        public void canEvaluateFullSize() {
            Pair<Integer, List<Integer>> page = Pagination.page(0, 2, Iterations.iterable(1, 2, 3, 4));
            Assert.assertEquals(Integer.valueOf(4), page.first());
        }

        @Test
        public void properlyPagesFirstPage() {
            Pair<Integer, List<Integer>> page = Pagination.page(0, 2, Iterations.iterable(1, 2, 3, 4));
            Assert.assertEquals(Arrays.asList(1, 2), page.second());
        }

        @Test
        public void properlyPagesOtherPages() {
            Pair<Integer, List<Integer>> page = Pagination.page(2, 2, Iterations.iterable(1, 2, 3, 4));
            Assert.assertEquals(Arrays.asList(3, 4), page.second());
        }

        @Test
        public void properlyPagesBounds() {
            Pair<Integer, List<Integer>> page = Pagination.page(3, 2, Iterations.iterable(1, 2, 3, 4));
            Assert.assertEquals(Arrays.asList(4), page.second());
        }

        @Test
        public void properlyReportsSizeWithBounds() {
            Pair<Integer, List<Integer>> page = Pagination.page(3, 2, Iterations.iterable(1, 2, 3, 4));
            Assert.assertEquals(Integer.valueOf(4), page.first());
        }

        @Test
        public void properlyPagesAfterBounds() {
            Pair<Integer, List<Integer>> page = Pagination.page(4, 2, Iterations.iterable(1, 2, 3, 4));
            Assert.assertEquals(Arrays.<Integer>asList(), page.second());
        }

        @Test
        public void properlyReportsSizeAfterBounds() {
            Pair<Integer, List<Integer>> page = Pagination.page(4, 2, Iterations.iterable(1, 2, 3, 4));
            Assert.assertEquals(Integer.valueOf(4), page.first());
        }

        @Test
        public void canPageIteratorToCollection() {
            final Iterable<Integer> iterable = Arrays.asList(1);
            Pair<Integer, ArrayList<Integer>> page = Pagination.page(0, 1, iterable.iterator(), new ArrayList<Integer>());
            Assert.assertEquals(Integer.valueOf(1), page.first());
        }

        @Test
        public void canPageLongIterable() {
            final Iterable<Integer> iterable = Arrays.asList(1);
            Pair<Long, List<Integer>> page = Pagination.LongPages.page(0l, 1l, iterable);
            Assert.assertEquals(Long.valueOf(1), page.first());
        }

        @Test
        public void canPageIterable() {
            final Iterable<Integer> iterable = Arrays.asList(1);
            Pair<Integer, List<Integer>> page = Pagination.page(0, 1, iterable);
            Assert.assertEquals(Integer.valueOf(1), page.first());
        }

        @Test
        public void canPageLongIterableToCollection() {
            final Iterable<Integer> iterable = Arrays.asList(1);
            Pair<Long, ArrayList<Integer>> page = Pagination.LongPages.page(0l, 1l, iterable, new ArrayList<Integer>());
            Assert.assertEquals(Long.valueOf(1), page.first());
        }

        @Test
        public void canPageIterableToCollection() {
            final Iterable<Integer> iterable = Arrays.asList(1);
            Pair<Integer, ArrayList<Integer>> page = Pagination.page(0, 1, iterable, new ArrayList<Integer>());
            Assert.assertEquals(Integer.valueOf(1), page.first());
        }

        @Test
        public void canPageLongArray() {
            Pair<Long, List<Integer>> page = Pagination.LongPages.page(0l, 1l, new Integer[]{1});
            Assert.assertEquals(Long.valueOf(1), page.first());
        }

        @Test
        public void canPageArray() {
            Pair<Integer, List<Integer>> page = Pagination.page(0, 1, new Integer[]{1});
            Assert.assertEquals(Integer.valueOf(1), page.first());
        }

        @Test
        public void canPageLongArrayToCollection() {
            Pair<Long, ArrayList<Integer>> page = Pagination.LongPages.page(0l, 1l, new Integer[]{1}, new ArrayList<Integer>());
            Assert.assertEquals(Long.valueOf(1), page.first());
        }

        @Test
        public void canPageArrayToCollection() {
            Pair<Integer, ArrayList<Integer>> page = Pagination.page(0, 1, new Integer[]{1}, new ArrayList<Integer>());
            Assert.assertEquals(Integer.valueOf(1), page.first());
        }

        @Test
        public void canPageLongCollection() {
            Pair<Long, List<Integer>> page = Pagination.LongPages.page(0l, 1l, Arrays.asList(1));
            Assert.assertEquals(Long.valueOf(1), page.first());
        }

        @Test
        public void canPageCollection() {
            Pair<Integer, List<Integer>> page = Pagination.page(0, 1, Arrays.asList(1));
            Assert.assertEquals(Integer.valueOf(1), page.first());
        }

        @Test
        public void canPageLongCollectionToCollection() {
            Pair<Long, ArrayList<Integer>> page = Pagination.LongPages.page(0l, 1l, Arrays.asList(1), new ArrayList<Integer>());
            Assert.assertEquals(Long.valueOf(1), page.first());
        }

        @Test
        public void canPageCollectionToCollection() {
            Pair<Integer, ArrayList<Integer>> page = Pagination.page(0, 1, Arrays.asList(1), new ArrayList<Integer>());
            Assert.assertEquals(Integer.valueOf(1), page.first());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallPageOnANullIterable() {
            final Iterable<Object> iterable = null;
            Pagination.page(4, 2, iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallPageOnNullIterableAndACollection() {
            final Iterable<Object> iterable = null;
            Pagination.page(4, 2, iterable, new ArrayList<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallPagelOnANullIterable() {
            final Iterable<Object> iterable = null;
            Pagination.LongPages.page(4, 2, iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallPagelOnNullIterableAndACollection() {
            final Iterable<Object> iterable = null;
            Pagination.LongPages.page(4, 2, iterable, new ArrayList<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallPagelOnANullCollection() {
            final Collection<Object> collection = null;
            Pagination.LongPages.page(4, 2, collection);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallPagelOnANullInputCollection() {
            final Collection<Object> collection = null;
            Pagination.LongPages.page(4, 2, collection, new ArrayList<Object>());
        }
    }

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Pagination() {
            };
        }
    }
}
