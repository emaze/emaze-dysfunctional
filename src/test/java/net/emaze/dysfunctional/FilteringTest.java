package net.emaze.dysfunctional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import org.junit.Assert;
import org.junit.Test;

/**
 * if you are going to add a test here, consider that Filtering should be just a
 * thin facade, and tests on FilteringTest should be just "smoke tests"
 *
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

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallFilterWithANullIterable() {
        final Iterable<Object> iterable = null;
        Filtering.filter(iterable, new Always<Object>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallAtMostLastWithANullIterable() {
        final Iterable<Object> iterable = null;
        Filtering.atMostLast(1, iterable);
    }

    @Test
    public void canTakeLastFromIterator() {
        Iterator<Integer> got = Filtering.takeLast(1, sampleList.iterator());
        Assert.assertEquals(Arrays.asList(2), Consumers.all(got));
    }

    @Test
    public void canTakeLastFromIterable() {
        Iterator<Integer> got = Filtering.takeLast(1, sampleList);
        Assert.assertEquals(Arrays.asList(2), Consumers.all(got));
    }

    @Test
    public void canTakeLastFromArray() {
        Iterator<Integer> got = Filtering.takeLast(1, sampleArray);
        Assert.assertEquals(Arrays.asList(2), Consumers.all(got));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallTakeLastOnANullIterable() {
        final Iterable<Object> iterable = null;
        Filtering.takeLast(1, iterable);
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
        Assert.assertEquals(Arrays.asList(1, 2), Consumers.all(got));
    }

    @Test
    public void testDrop() {
        Iterator<Integer> got = Filtering.drop(1, sampleList.iterator());
        Assert.assertEquals(Arrays.asList(2), Consumers.all(got));
    }

    @Test
    public void canSliceEmptyIterator() {
        List<Object> source = Collections.emptyList();
        Assert.assertEquals(source, Consumers.all(Filtering.slice(0, 10, source.iterator())));
    }

    @Test
    public void canSliceNonEmptyIterator() {
        List<Integer> source = Arrays.asList(1, 2, 3, 4);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        List<Integer> got = Consumers.all(Filtering.slice(0, 3, source.iterator()));
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canSliceNonEmptyIteratorWithOffset() {
        List<Integer> source = Arrays.asList(0, 1);
        List<Integer> expected = Arrays.asList(1);
        List<Integer> got = Consumers.all(Filtering.slice(1, 1, source.iterator()));
        Assert.assertEquals(expected, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSliceANullIterable() {
        final Iterable<Object> iterable = null;
        Filtering.slice(1, 2, iterable);
    }

    @Test
    public void canSliceAnArray() {
        final Integer[] source = {0, 1};
        final List<Integer> expected = Arrays.asList(1);
        final List<Integer> got = Consumers.all(Filtering.slice(1, 1, source));
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canGetAtMostLastElement() {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4);
        final Integer expected = list.get(list.size() - 1);
        final Integer last = Filtering.atMostLast(1, list).next();
        Assert.assertEquals(expected, last);
    }

    @Test
    public void canGetAtMostLastElementFromAnArray() {
        final Integer[] arr = {1, 2, 3, 4};
        final Integer expected = arr[arr.length - 1];
        final Integer last = Filtering.atMostLast(1, arr).next();
        Assert.assertEquals(expected, last);
    }

    @Test
    public void canGetAtMostAllElements() {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4);
        final Iterator<Integer> atMostAllElements = Filtering.atMostLast(list.size(), list);
        final List<Integer> got = Consumers.all(atMostAllElements);
        Assert.assertEquals(list, got);
    }

    @Test
    public void canGetAtMostBeyondInputSize() {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4);
        final Iterator<Integer> atMostAllElements = Filtering.atMostLast(list.size() + 2, list);
        final List<Integer> got = Consumers.all(atMostAllElements);
        Assert.assertEquals(list, got);
    }

    @Test
    public void facadeIsNotFinal() {
        new Filtering() {
        };
    }
}
