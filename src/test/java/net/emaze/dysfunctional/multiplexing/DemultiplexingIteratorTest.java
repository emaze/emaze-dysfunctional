package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.Dispatching;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
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

    final static Provider<List<Integer>> LIST_FACTORY = Dispatching.compose(new Narrow<List<Integer>, ArrayList<Integer>>(), new ArrayListFactory<Integer>());

    
    public static class Functions {

        @Test
        public void canVerifyIfAnIteratorHasNext() {
            List<Integer> backingList = Arrays.asList(1);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<List<Integer>, Integer>(1, backingList.iterator(), LIST_FACTORY);
            Assert.assertTrue(iter.hasNext());
        }

        @Test
        public void callingHasNextDoesNotConsumeIterator() {
            List<Integer> backingList = Arrays.asList(1);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<List<Integer>, Integer>(1, backingList.iterator(), LIST_FACTORY);
            iter.hasNext();
            Assert.assertTrue(iter.hasNext());
        }

        @Test
        public void callingHasNextOnTooShortIteratorYieldsFalse() {
            List<Integer> backingList = Arrays.asList(1);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<List<Integer>, Integer>(2, backingList.iterator(), LIST_FACTORY);
            Assert.assertFalse(iter.hasNext());
        }

        @Test
        public void canConsumeAnElementCallingHasNextBefore() {
            List<Integer> backingList = Arrays.asList(1, 2);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<List<Integer>, Integer>(2, backingList.iterator(), LIST_FACTORY);
            iter.hasNext();
            Assert.assertEquals(backingList, iter.next());
        }

        @Test
        public void canConsumeElementsWithoutCallingHasNext() {
            List<Integer> backingList = Arrays.asList(1, 2);
            Iterator<List<Integer>> iter = new DemultiplexingIterator<List<Integer>, Integer>(2, backingList.iterator(), LIST_FACTORY);
            Assert.assertEquals(backingList, iter.next());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithNullIteratorYieldsException() {
            new DemultiplexingIterator<List<Integer>, Integer>(1, null, LIST_FACTORY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithZeroChannelsYieldsException() {
            new DemultiplexingIterator<List<Integer>, Integer>(0, Collections.<Integer>emptyList().iterator(), LIST_FACTORY);
        }
        @Test(expected = IllegalArgumentException.class)
        public void creatingWithNullListFactoryYieldsException() {
            new DemultiplexingIterator<List<Integer>, Integer>(1, Collections.<Integer>emptyList().iterator(), null);
        }

        @Test(expected = NoSuchElementException.class)
        public void consumingNonSquaredIteratoryYieldsException() {
            Iterator<List<Integer>> iter = new DemultiplexingIterator<List<Integer>, Integer>(2, Arrays.asList(1, 2, 2).iterator(), LIST_FACTORY);
            iter.next();
            iter.hasNext();
            iter.next();
        }
    }
}
