package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import net.emaze.dysfunctional.consumers.Consumers;
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
    MultiplexingIteratorTest.Degenerations.class,
    MultiplexingIteratorTest.Functions.class
})
public class MultiplexingIteratorTest {

    public static class Functions {

        @Test
        public void canMultiplexFromManyIterators() {
            final Iterator<Integer> odds = Arrays.asList(1, 3).iterator();
            final Iterator<Integer> evens = Arrays.asList(2, 4).iterator();
            final Iterator<Iterator<Integer>> oddsAndEvens = Arrays.asList(odds, evens).iterator();
            Iterator<Integer> iter = new MultiplexingIterator<Integer>(oddsAndEvens);
            Assert.assertEquals(Arrays.asList(1, 2, 3, 4), Consumers.all(iter));
        }

        @Test
        public void canMultiplexIteratorsNotOfTheSameSize() {
            final Iterator<Integer> former = Arrays.asList(1, 3).iterator();
            final Iterator<Integer> latter = Arrays.asList(2).iterator();
            final Iterator<Iterator<Integer>> formerAndLatter = Arrays.asList(former, latter).iterator();
            Iterator<Integer> iter = new MultiplexingIterator<Integer>(formerAndLatter);
            Assert.assertEquals(Arrays.asList(1, 2), Consumers.all(iter));
        }

        @Test
        public void nonEmptyIteratorHasNext() {
            final Iterator<Integer> inner = Arrays.asList(1).iterator();
            final Iterator<Iterator<Integer>> onlyInner = Collections.singletonList(inner).iterator();
            Iterator<Integer> iter = new MultiplexingIterator<Integer>(onlyInner);
            Assert.assertTrue(iter.hasNext());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithNullIteratorArrayYieldsException() {
            new MultiplexingIterator<Object>(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithEmptyIteratorsYieldsException() {
            new MultiplexingIterator<Object>(Collections.<Iterator<Object>>emptyList().iterator());
        }
    }
}
