package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Iterations;
import java.util.Optional;
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
    RoundrobinLongestIteratorTest.Functions.class,
    RoundrobinLongestIteratorTest.Degenerations.class
})
public class RoundrobinLongestIteratorTest {

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCreateMultiplexingIteratorWithNullIterators() {
            new RoundrobinLongestIterator<Object>(null);
        }
    }

    public static class Functions {

        @Test
        public void hasNoNextWhenEveryIteratorIsEmpty() {
            Iterator<Integer> first = Arrays.<Integer>asList().iterator();
            Iterator<Integer> second = Arrays.<Integer>asList().iterator();
            Iterator<Iterator<Integer>> firstAndSecond = Arrays.asList(first, second).iterator();
            Assert.assertFalse(new RoundrobinLongestIterator<Integer>(firstAndSecond).hasNext());
        }

        @Test
        public void canConsumeIterator() {
            Iterator<Integer> odds = Arrays.asList(1, 3).iterator();
            Iterator<Integer> evens = Arrays.asList(2, 4).iterator();
            Iterator<Iterator<Integer>> oddsAndEvens = Arrays.asList(odds, evens).iterator();
            RoundrobinLongestIterator<Integer> iter = new RoundrobinLongestIterator<Integer>(oddsAndEvens);
            List<Optional<Integer>> got = Consumers.all(iter);
            List<Optional<Integer>> expected = Arrays.asList(Optional.of(1), Optional.of(2), Optional.of(3), Optional.of(4));
            Assert.assertEquals(expected, got);
        }

        @Test
        public void gretestIteratorSizeIsTheChannelSize() {
            Iterator<Integer> odds = Arrays.asList(1, 3, 5).iterator();
            Iterator<Integer> evens = Arrays.asList(2, 4).iterator();
            Iterator<Iterator<Integer>> oddsAndEvens = Arrays.asList(odds, evens).iterator();
            RoundrobinLongestIterator<Integer> iter = new RoundrobinLongestIterator<Integer>(oddsAndEvens);
            List<Optional<Integer>> got = Consumers.all(iter);
            List<Optional<Integer>> expected = Arrays.asList(Optional.of(1), Optional.of(2), Optional.of(3), Optional.of(4), Optional.of(5), Optional.<Integer>empty());
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canEvaluatesizeWithMultipleChannels() {
            final Iterator<Integer> odds = Arrays.asList(1, 3, 5).iterator();
            final Iterator<Integer> evens = Arrays.<Integer>asList().iterator();
            final Iterator<Iterator<Integer>> oddsAndEvens = Arrays.asList(odds, evens, evens, evens).iterator();
            final RoundrobinLongestIterator<Integer> iter = new RoundrobinLongestIterator<Integer>(oddsAndEvens);
            final int size = Consumers.all(iter).size();
            Assert.assertEquals(12, size);
        }

        @Test
        public void creatingWithEmptyIteratorOfIteratorsYieldsEmptyIterator() {
            RoundrobinLongestIterator<O> iterator = new RoundrobinLongestIterator<O>(Iterations.<Iterator<O>>iterator());
            Assert.assertFalse(iterator.hasNext());
        }

        @Test(expected = NoSuchElementException.class)
        public void callingNextOnEmptyIteratorYieldsException() {
            RoundrobinLongestIterator<O> iterator = new RoundrobinLongestIterator<O>(Iterations.<Iterator<O>>iterator());
            iterator.next();
        }

        @Test(expected = NoSuchElementException.class)
        public void callingNextAfterBoundsYieldsException() {
            RoundrobinLongestIterator<O> iterator = new RoundrobinLongestIterator<O>(Iterations.iterator(Iterations.iterator(O.ONE)));
            iterator.next();
            iterator.next();
        }
    }
}
