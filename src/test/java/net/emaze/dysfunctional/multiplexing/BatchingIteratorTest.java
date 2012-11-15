package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BatchingIteratorTest {

    @Test
    public void callingHasNextDoesNotConsume() {
        final Iterator<ArrayList<O>> iter = new BatchingIterator<ArrayList<O>, O>(1, Iterations.iterator(O.ONE), new ArrayListFactory<O>());
        iter.hasNext();
        Assert.assertTrue(iter.hasNext());
    }

    @Test
    public void consumesUpToBatchSize() {
        final Iterator<ArrayList<O>> iter = new BatchingIterator<ArrayList<O>, O>(2, Iterations.iterator(O.ONE, O.ANOTHER, O.YET_ANOTHER), new ArrayListFactory<O>());
        Assert.assertEquals(Arrays.asList(Arrays.asList(O.ONE, O.ANOTHER), Arrays.asList(O.YET_ANOTHER)), Consumers.all(iter));
    }

    @Test
    public void canConsumeWithoutCallingHasNext() {
        final Iterator<ArrayList<O>> iter = new BatchingIterator<ArrayList<O>, O>(1, Iterations.iterator(O.ONE, O.ANOTHER), new ArrayListFactory<O>());
        Assert.assertEquals(Arrays.asList(Arrays.asList(O.ONE), Arrays.asList(O.ANOTHER)), Arrays.asList(iter.next(), iter.next()));
    }

    @Test(expected = NoSuchElementException.class)
    public void consumingAfterBoundariesYieldsException() {
        final Iterator<ArrayList<O>> iter = new BatchingIterator<ArrayList<O>, O>(1, Iterations.<O>iterator(), new ArrayListFactory<O>());
        iter.next();
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNonPositiveBatchSizeYieldsException() {
        new BatchingIterator<ArrayList<O>, O>(0, Iterations.iterator(O.ONE), new ArrayListFactory<O>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIteratorYieldsException() {
        new BatchingIterator<ArrayList<O>, O>(1, null, new ArrayListFactory<O>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullChannelProviderYieldsException() {
        new BatchingIterator<ArrayList<O>, O>(1, Iterations.iterator(O.ONE), null);
    }
}