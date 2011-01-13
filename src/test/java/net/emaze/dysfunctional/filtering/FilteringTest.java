package net.emaze.dysfunctional.filtering;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.consumers.EagerConsumer;
import net.emaze.dysfunctional.delegates.Always;
import net.emaze.dysfunctional.delegates.Never;
import org.junit.Assert;
import org.junit.Test;

/**
 * if you are going to add a test here, consider that Filtering should be just
 * a thin facade, and tests on FilteringTest should be just "smoke tests"
 * @author rferranti
 */
public class FilteringTest {

    final List<Integer> sampleList = Arrays.asList(1, 2);
    final Integer[] sampleArray = new Integer[]{1, 2};

    @Test
    public void canFilterAnIterator() {
        Iterator<Integer> got = Filtering.filter(sampleList.iterator(), new Never<Integer>());
        Assert.assertEquals(0, Consumers.all(got).size());
    }

    @Test
    public void canFilterAnIterable() {
        Iterator<Integer> got = Filtering.filter(sampleList, new Never<Integer>());
        Assert.assertEquals(0, Consumers.all(got).size());
    }

    @Test
    public void canFilterAnArray() {
        Iterator<Integer> got = Filtering.filter(sampleArray, new Never<Integer>());
        Assert.assertEquals(0, Consumers.all(got).size());
    }

    @Test
    public void canFetchFirstFromIterator() {
        Iterator<Integer> got = Filtering.first(1, sampleList.iterator());
        Assert.assertEquals(Arrays.asList(1), Consumers.all(got));
    }

    @Test
    public void canFetchFirstFromIterable() {
        Iterator<Integer> got = Filtering.first(1, sampleList);
        Assert.assertEquals(Arrays.asList(1), Consumers.all(got));
    }

    @Test
    public void canFetchFirstFromArray() {
        Iterator<Integer> got = Filtering.first(1, sampleArray);
        Assert.assertEquals(Arrays.asList(1), Consumers.all(got));
    }

    @Test
    public void canFetchLastFromIterator() {
        Iterator<Integer> got = Filtering.last(1, sampleList.iterator());
        Assert.assertEquals(Arrays.asList(2), Consumers.all(got));
    }

    @Test
    public void canFetchLastFromIterable() {
        Iterator<Integer> got = Filtering.last(1, sampleList);
        Assert.assertEquals(Arrays.asList(2), Consumers.all(got));
    }

    @Test
    public void canFetchLastFromArray() {
        Iterator<Integer> got = Filtering.last(1, sampleArray);
        Assert.assertEquals(Arrays.asList(2), Consumers.all(got));
    }

    @Test
    public void canTakeWhileFromIterator() {
        Iterator<Integer> got = Filtering.takeWhile(sampleList.iterator(), new Never<Integer>());
        Assert.assertEquals(0, Consumers.all(got).size());
    }

    @Test
    public void canDropWhileFromIterator() {
        Iterator<Integer> got = Filtering.dropWhile(sampleList.iterator(), new Always<Integer>());
        Assert.assertEquals(0, Consumers.all(got).size());
    }

    @Test
    public void canTakeFromIterator() {
        Iterator<Integer> got = Filtering.take(2, sampleList.iterator());
        Assert.assertEquals(Arrays.asList(1,2), Consumers.all(got));
    }

    @Test
    public void testDrop() {
        Iterator<Integer> got = Filtering.drop(1, sampleList.iterator());
        Assert.assertEquals(Arrays.asList(2), Consumers.all(got));
    }

    @Test
    public void canFetchNthFromIterator() {
        Assert.assertEquals(Integer.valueOf(1), Filtering.nth(1, sampleList.iterator()).next() );
    }

    @Test
    public void canFetchNthFromIterable() {
        Assert.assertEquals(Integer.valueOf(1), Filtering.nth(1, sampleList).next());
    }

    @Test
    public void canFetchNthFromArray() {
        Assert.assertEquals(Integer.valueOf(1), Filtering.nth(1, sampleArray).next());
    }

    @Test
    public void canFetchAtFromIterator() {
        Assert.assertEquals(Integer.valueOf(1), Filtering.at(0, sampleList.iterator()).next() );
    }

    @Test
    public void canFetchAtFromIterable() {
        Assert.assertEquals(Integer.valueOf(1), Filtering.at(0, sampleList).next() );
    }

    @Test
    public void canFetchAtFromArray() {
        Assert.assertEquals(Integer.valueOf(1), Filtering.at(0, sampleArray).next() );
    }
}
