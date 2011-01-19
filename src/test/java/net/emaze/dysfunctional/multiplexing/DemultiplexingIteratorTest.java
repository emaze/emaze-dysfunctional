package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
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
    DemultiplexingIteratorTest.Functions.class,
    DemultiplexingIteratorTest.Degenerations.class
})
public class DemultiplexingIteratorTest {

    public static class Functions {

        @Test
        public void canVerifyIfAnIteratorHasNext() {
            List<Integer> backingList = Arrays.asList(1);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(1, backingList.iterator());
            Assert.assertTrue(iter.hasNext());
        }

        @Test
        public void callingHasNextDoesNotConsumeIterator() {
            List<Integer> backingList = Arrays.asList(1);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(1, backingList.iterator());
            iter.hasNext();
            Assert.assertTrue(iter.hasNext());
        }

        @Test
        public void callingHasNextOnTooShortIteratorYieldsFalse() {
            List<Integer> backingList = Arrays.asList(1);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(2, backingList.iterator());
            Assert.assertFalse(iter.hasNext());
        }

        @Test
        public void canConsumeAnElementCallingHasNextBefore() {
            List<Integer> backingList = Arrays.asList(1, 2);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(2, backingList.iterator());
            iter.hasNext();
            Assert.assertEquals(backingList, iter.next());
        }

        @Test
        public void canConsumeElementsWithoutCallingHasNext() {
            List<Integer> backingList = Arrays.asList(1, 2);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(2, backingList.iterator());
            Assert.assertEquals(backingList, iter.next());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithNullIteratorYieldsException() {
            new DemultiplexingIterator<Object>(1, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithZeroChannelsYieldsException() {
            new DemultiplexingIterator<Object>(0, Collections.emptyList().iterator());
        }

        @Test(expected = NoSuchElementException.class)
        public void consumingNonSquaredIteratoryYieldsException() {
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(2, Arrays.asList(1, 2, 2).iterator());
            iter.next();
            iter.hasNext();
            iter.next();
        }

        @Test(expected = UnsupportedOperationException.class)
        public void removingFromDemultiplexingIteratorYieldsException() {
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(2, Arrays.asList(1, 2).iterator());
            iter.next();
            iter.remove();
        }
    }
}
