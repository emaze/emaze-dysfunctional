package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
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
            Iterator<Integer> iter = new MultiplexingIterator<Integer>(odds, evens);
            Assert.assertEquals(Arrays.asList(1, 2, 3, 4), Consumers.all(iter));
        }

        @Test
        public void canMultiplexIteratorsNotOfTheSameSize() {
            final Iterator<Integer> former = Arrays.asList(1,3).iterator();
            final Iterator<Integer> latter = Arrays.asList(2).iterator();
            Iterator<Integer> iter = new MultiplexingIterator<Integer>(former, latter);
            Assert.assertEquals(Arrays.asList(1, 2), Consumers.all(iter));
        }

        @Test
        public void nonEmptyIteratorHasNext() {
            final Iterator<Integer> inner = Arrays.asList(1).iterator();
            Iterator<Integer> iter = new MultiplexingIterator<Integer>(inner);
            Assert.assertTrue(iter.hasNext());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithNullIteratorArrayYieldsException() {
            new MultiplexingIterator<Object>((Iterator<Object>[]) null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithEmptyIteratorsYieldsException() {
            new MultiplexingIterator<Object>();
        }

        @Test(expected = UnsupportedOperationException.class)
        public void removingFromIteratorYieldsException() {
            Iterator<Integer> iter = new MultiplexingIterator<Integer>(Arrays.asList(1).iterator());
            iter.next();
            iter.remove();
        }
    }
}
