package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
import java.util.Iterator;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
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
    RoundRobinShortestIteratorTest.Degenerations.class,
    RoundRobinShortestIteratorTest.Functions.class
})
public class RoundRobinShortestIteratorTest {

    public static class Functions {

        @Test
        public void canMultiplexFromManyIterators() {
            final Iterator<Integer> odds = Iterations.iterator(1, 3);
            final Iterator<Integer> evens = Iterations.iterator(2, 4);
            final Iterator<Iterator<Integer>> oddsAndEvens = Iterations.iterator(odds, evens);
            final Iterator<Integer> iter = new RoundRobinShortestIterator<Integer>(oddsAndEvens);
            Assert.assertEquals(Arrays.asList(1, 2, 3, 4), Consumers.all(iter));
        }

        @Test
        public void canMultiplexIteratorsNotOfTheSameSize() {
            final Iterator<Integer> former = Iterations.iterator(1, 3);
            final Iterator<Integer> latter = Iterations.iterator(2);
            final Iterator<Iterator<Integer>> formerAndLatter = Iterations.iterator(former, latter);
            final Iterator<Integer> iter = new RoundRobinShortestIterator<Integer>(formerAndLatter);
            Assert.assertEquals(Arrays.asList(1, 2), Consumers.all(iter));
        }

        @Test
        public void nonEmptyIteratorHasNext() {
            final Iterator<Integer> inner = Iterations.iterator(1);
            final Iterator<Iterator<Integer>> onlyInner = Iterations.iterator(inner);
            final Iterator<Integer> iter = new RoundRobinShortestIterator<Integer>(onlyInner);
            Assert.assertTrue(iter.hasNext());
        }

        @Test
        public void callingNextYieldsFirstValue() {
            final Iterator<Integer> inner = Iterations.iterator(1);
            final Iterator<Iterator<Integer>> onlyInner = Iterations.iterator(inner);
            final Iterator<Integer> iter = new RoundRobinShortestIterator<Integer>(onlyInner);
            Assert.assertEquals(1, iter.next().intValue());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithNullIteratorArrayYieldsException() {
            new RoundRobinShortestIterator<O>(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithEmptyIteratorsYieldsException() {
            new RoundRobinShortestIterator<O>(Iterations.<Iterator<O>>iterator());
        }
    }
}
