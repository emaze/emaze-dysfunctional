package net.emaze.dysfunctional.pagination;

import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PaginationTest {

    @Test
    public void canEvaluateFullSize() {
        Pair<Integer, List<Integer>> page = Pagination.page(0, 2, Arrays.asList(1,2,3,4).iterator());
        Assert.assertEquals(Integer.valueOf(4), page.first());
    }
    
    @Test
    public void properlyPagesFirstPage() {
        Pair<Integer, List<Integer>> page = Pagination.page(0, 2, Arrays.asList(1,2,3,4).iterator());
        Assert.assertEquals(Arrays.asList(1,2), page.second());
    }
    
    @Test
    public void properlyPagesOtherPages() {
        Pair<Integer, List<Integer>> page = Pagination.page(2, 2, Arrays.asList(1,2,3,4).iterator());
        Assert.assertEquals(Arrays.asList(3,4), page.second());
    }
    
    @Test
    public void properlyPagesBounds() {
        Pair<Integer, List<Integer>> page = Pagination.page(3, 2, Arrays.asList(1,2,3,4).iterator());
        Assert.assertEquals(Arrays.asList(4), page.second());
    }
    
    @Test
    public void properlyReportsSizeWithBounds() {
        Pair<Integer, List<Integer>> page = Pagination.page(3, 2, Arrays.asList(1,2,3,4).iterator());
        Assert.assertEquals(Integer.valueOf(4), page.first());
    }
    
    @Test
    public void properlyPagesAfterBounds() {
        Pair<Integer, List<Integer>> page = Pagination.page(4, 2, Arrays.asList(1,2,3,4).iterator());
        Assert.assertEquals(Arrays.<Integer>asList(), page.second());
    }

    @Test
    public void properlyReportsSizeAfterBounds() {
        Pair<Integer, List<Integer>> page = Pagination.page(4, 2, Arrays.asList(1,2,3,4).iterator());
        Assert.assertEquals(Integer.valueOf(4), page.first());
    }

}