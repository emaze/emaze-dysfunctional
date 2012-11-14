package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@SuiteClasses({
    RoundRobinLongestIteratorTest.Functions.class,
    RoundRobinLongestIteratorTest.Degenerations.class
})
public class RoundRobinLongestIteratorTest {

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCreateMultiplexingIteratorWithNullIterators() {
            new RoundRobinLongestIterator<Object>(null);
        }
    }

    public static class Functions {

        @Test
        public void hasNoNextWhenEveryIteratorIsEmpty() {
            Iterator<Integer> first = Arrays.<Integer>asList().iterator();
            Iterator<Integer> second = Arrays.<Integer>asList().iterator();
            Iterator<Iterator<Integer>> firstAndSecond = Arrays.asList(first, second).iterator();
            Assert.assertFalse(new RoundRobinLongestIterator<Integer>(firstAndSecond).hasNext());
        }

        @Test
        public void canConsumeIterator() {
            Iterator<Integer> odds = Arrays.asList(1, 3).iterator();
            Iterator<Integer> evens = Arrays.asList(2, 4).iterator();
            Iterator<Iterator<Integer>> oddsAndEvens = Arrays.asList(odds, evens).iterator();
            RoundRobinLongestIterator<Integer> iter = new RoundRobinLongestIterator<Integer>(oddsAndEvens);
            List<Maybe<Integer>> got = Consumers.all(iter);
            List<Maybe<Integer>> expected = Arrays.asList(Maybe.just(1), Maybe.just(2), Maybe.just(3), Maybe.just(4));
            Assert.assertEquals(expected, got);
        }

        @Test
        public void gretestIteratorSizeIsTheChannelSize() {
            Iterator<Integer> odds = Arrays.asList(1, 3, 5).iterator();
            Iterator<Integer> evens = Arrays.asList(2, 4).iterator();
            Iterator<Iterator<Integer>> oddsAndEvens = Arrays.asList(odds, evens).iterator();
            RoundRobinLongestIterator<Integer> iter = new RoundRobinLongestIterator<Integer>(oddsAndEvens);
            List<Maybe<Integer>> got = Consumers.all(iter);
            List<Maybe<Integer>> expected = Arrays.asList(Maybe.just(1), Maybe.just(2), Maybe.just(3), Maybe.just(4), Maybe.just(5), Maybe.<Integer>nothing());
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canEvaluatesizeWithMultipleChannels() {
            final Iterator<Integer> odds = Arrays.asList(1, 3, 5).iterator();
            final Iterator<Integer> evens = Arrays.<Integer>asList().iterator();
            final Iterator<Iterator<Integer>> oddsAndEvens = Arrays.asList(odds, evens, evens, evens).iterator();
            final RoundRobinLongestIterator<Integer> iter = new RoundRobinLongestIterator<Integer>(oddsAndEvens);
            final int size = Consumers.all(iter).size();
            Assert.assertEquals(12, size);
        }

        @Test
        public void creatingWithEmptyIteratorOfIteratorsYieldsEmptyIterator() {
            RoundRobinLongestIterator<O> iterator = new RoundRobinLongestIterator<O>(Iterations.<Iterator<O>>iterator());
            Assert.assertFalse(iterator.hasNext());
        }

        @Test(expected = NoSuchElementException.class)
        public void callingNextOnEmptyIteratorYieldsException() {
            RoundRobinLongestIterator<O> iterator = new RoundRobinLongestIterator<O>(Iterations.<Iterator<O>>iterator());
            iterator.next();
        }

        @Test(expected = NoSuchElementException.class)
        public void callingNextAfterBoundsYieldsException() {
            RoundRobinLongestIterator<O> iterator = new RoundRobinLongestIterator<O>(Iterations.iterator(Iterations.iterator(O.ONE)));
            iterator.next();
            iterator.next();
        }
    }
}
