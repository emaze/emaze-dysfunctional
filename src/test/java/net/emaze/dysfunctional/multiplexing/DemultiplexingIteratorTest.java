package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
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
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(backingList.iterator(), 1);
            Assert.assertTrue(iter.hasNext());
        }

        @Test
        public void callingHasNextDoesNotConsumeIterator() {
            List<Integer> backingList = Arrays.asList(1);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(backingList.iterator(), 1);
            iter.hasNext();
            Assert.assertTrue(iter.hasNext());
        }

        @Test
        public void callingHasNextOnTooShortIteratorYieldsFalse() {
            List<Integer> backingList = Arrays.asList(1);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(backingList.iterator(), 2);
            Assert.assertFalse(iter.hasNext());
        }

        @Test
        public void canConsumeAnElementCallingHasNextBefore() {
            List<Integer> backingList = Arrays.asList(1, 2);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(backingList.iterator(), 2);
            iter.hasNext();
            Assert.assertEquals(backingList, iter.next());
        }

        @Test
        public void canConsumeElementsWithoutCallingHasNext() {
            List<Integer> backingList = Arrays.asList(1, 2);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(backingList.iterator(), 2);
            Assert.assertEquals(backingList, iter.next());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithNullIteratorYieldsException() {
            new DemultiplexingIterator<Object>(null, 1);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithZeroChannelsYieldsException() {
            new DemultiplexingIterator<Object>(Arrays.asList().iterator(), 0);
        }

        @Test(expected = NoSuchElementException.class)
        public void consumingNonSquaredIteratoryYieldsException() {
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(Arrays.asList(1, 2, 2).iterator(), 2);
            iter.next();
            iter.next();
        }

        @Test(expected = UnsupportedOperationException.class)
        public void removingFromDemultiplexingIteratorYieldsException() {
            Iterator<List<Integer>> iter = new DemultiplexingIterator<Integer>(Arrays.asList(1, 2).iterator(), 2);
            iter.next();
            iter.remove();
        }
    }
}
