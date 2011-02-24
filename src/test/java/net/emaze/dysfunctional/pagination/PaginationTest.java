package net.emaze.dysfunctional.pagination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class PaginationTest {

    @Test
    public void canEvaluateFullSize() {
        Pair<Integer, List<Integer>> page = Pagination.page(0, 2, Arrays.asList(1, 2, 3, 4).iterator());
        Assert.assertEquals(Integer.valueOf(4), page.first());
    }

    @Test
    public void properlyPagesFirstPage() {
        Pair<Integer, List<Integer>> page = Pagination.page(0, 2, Arrays.asList(1, 2, 3, 4).iterator());
        Assert.assertEquals(Arrays.asList(1, 2), page.second());
    }

    @Test
    public void properlyPagesOtherPages() {
        Pair<Integer, List<Integer>> page = Pagination.page(2, 2, Arrays.asList(1, 2, 3, 4).iterator());
        Assert.assertEquals(Arrays.asList(3, 4), page.second());
    }

    @Test
    public void properlyPagesBounds() {
        Pair<Integer, List<Integer>> page = Pagination.page(3, 2, Arrays.asList(1, 2, 3, 4).iterator());
        Assert.assertEquals(Arrays.asList(4), page.second());
    }

    @Test
    public void properlyReportsSizeWithBounds() {
        Pair<Integer, List<Integer>> page = Pagination.page(3, 2, Arrays.asList(1, 2, 3, 4).iterator());
        Assert.assertEquals(Integer.valueOf(4), page.first());
    }

    @Test
    public void properlyPagesAfterBounds() {
        Pair<Integer, List<Integer>> page = Pagination.page(4, 2, Arrays.asList(1, 2, 3, 4).iterator());
        Assert.assertEquals(Arrays.<Integer>asList(), page.second());
    }

    @Test
    public void properlyReportsSizeAfterBounds() {
        Pair<Integer, List<Integer>> page = Pagination.page(4, 2, Arrays.asList(1, 2, 3, 4).iterator());
        Assert.assertEquals(Integer.valueOf(4), page.first());
    }

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
        Pagination.pagel(4, 2, iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallPagelOnNullIterableAndACollection() {
        final Iterable<Object> iterable = null;
        Pagination.pagel(4, 2, iterable, new ArrayList<Object>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallPagelOnANullCollection() {
        final Collection<Object> collection = null;
        Pagination.pagel(4, 2, collection);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallPagelOnANullInputCollection() {
        final Collection<Object> collection = null;
        Pagination.pagel(4, 2, collection, new ArrayList<Object>());
    }
}
