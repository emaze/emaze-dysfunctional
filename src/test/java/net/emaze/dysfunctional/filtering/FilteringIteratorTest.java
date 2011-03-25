package net.emaze.dysfunctional.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import net.emaze.dysfunctional.iterations.ConstantIterator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FilteringIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingFilteringIteratorWithNullIteratoryYieldsException() {
        new FilteringIterator<Object>(null, new Always<Object>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingFilteringIteratorWithNullPredicateYieldsException() {
        new FilteringIterator<Object>(new ConstantIterator<Object>("a"), null);
    }

    @Test(expected = NoSuchElementException.class)
    public void callingNextOnEmptyIteratoryYieldsException() {
        List<Integer> bucket = Collections.<Integer>emptyList();
        Iterator<Integer> iter = new FilteringIterator<Integer>(bucket.iterator(), new Always<Integer>());
        iter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void callingNextOnNeverMatchingIteratoryYieldsException() {
        List<Integer> bucket = Arrays.asList(1, 2, 3);
        Iterator<Integer> iter = new FilteringIterator<Integer>(bucket.iterator(), new Never<Integer>());
        iter.next();
    }

    @Test
    public void callingHasNextDoesNotConsumeValues() {
        List<Integer> bucket = Arrays.asList(1);
        Iterator<Integer> iter = new FilteringIterator<Integer>(bucket.iterator(), new Always<Integer>());
        iter.hasNext();
        iter.hasNext();
        Assert.assertEquals(Integer.valueOf(1), iter.next());
    }

    @Test
    public void emptyIteratorHasNoNext() {
        List<Integer> bucket = Collections.emptyList();
        Iterator<Integer> iter = new FilteringIterator<Integer>(bucket.iterator(), new Always<Integer>());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void whenPredicateEvaluatesToFalseElementsAreFilteredOut() {
        List<Integer> bucket = Collections.singletonList(1);
        Iterator<Integer> iter = new FilteringIterator<Integer>(bucket.iterator(), new Never<Integer>());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void canCallNextMultipleTimesWithoutCallingHasNext() {
        List<Integer> bucket = Arrays.asList(1, 2);
        Iterator<Integer> iter = new FilteringIterator<Integer>(bucket.iterator(), new Always<Integer>());
        List<Integer> got = new ArrayList<Integer>();
        got.add(iter.next());
        got.add(iter.next());
        Assert.assertEquals(bucket, got);
    }

    @Test
    public void canRemoveFromFilteringIterator() {
        List<Integer> bucket = new ArrayList<Integer>();
        bucket.add(1);
        bucket.add(2);

        Iterator<Integer> iter = new FilteringIterator<Integer>(bucket.iterator(), new Always<Integer>());
        iter.next();
        iter.remove();

        Assert.assertEquals(Arrays.asList(2), bucket);
    }
}
