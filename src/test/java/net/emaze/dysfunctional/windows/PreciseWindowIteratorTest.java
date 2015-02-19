package net.emaze.dysfunctional.windows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.Compositions;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.collections.LinkedListFactory;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PreciseWindowIteratorTest {

    private static Provider<List<O>> LIST_FACTORY = Compositions.compose(new Vary<ArrayList<O>, List<O>>(), new ArrayListFactory<O>());

    @Test(expected = IllegalArgumentException.class)
    public void creatingPreciseWindowIteratorWithNullIteratorYieldsException() {
        new PreciseWindowIterator<List<O>, O>(null, 1, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingPreciseWindowIteratorWithNonPositiveWindowSizeYieldsException() {
        new PreciseWindowIterator<List<O>, O>(Collections.<O>emptyList().iterator(), 0, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingPreciseWindowIteratorWithNullProviderYieldsException() {
        new PreciseWindowIterator<List<O>, O>(Collections.<O>emptyList().iterator(), 1, null);
    }

    @Test(expected = NoSuchElementException.class)
    public void consumingEmptyIteratorYieldsException() {
        new PreciseWindowIterator<List<O>, O>(Collections.<O>emptyList().iterator(), 3, LIST_FACTORY).next();
    }

    @Test
    public void nonEmptyIterHasNext() {
        final Iterator<O> iter = Iterations.iterator(O.ONE);
        final PreciseWindowIterator<List<O>, O> win = new PreciseWindowIterator<List<O>, O>(iter, 1, LIST_FACTORY);
        Assert.assertTrue(win.hasNext());
    }

    @Test
    public void canCallHasNextTwoTimes() {
        final Iterator<O> iter = Iterations.iterator(O.ONE);
        final PreciseWindowIterator<List<O>, O> win = new PreciseWindowIterator<List<O>, O>(iter, 1, LIST_FACTORY);
        win.hasNext();
        Assert.assertTrue(win.hasNext());
    }

    @Test
    public void emptyIterHasNoNext() {
        final Iterator<O> iter = Iterations.iterator();
        final PreciseWindowIterator<List<O>, O> win = new PreciseWindowIterator<List<O>, O>(iter, 1, LIST_FACTORY);
        Assert.assertFalse(win.hasNext());
    }
    
    @Test
    public void hasNextDoesNotConsumeAnElement() {
        Iterator<Integer> iter = Arrays.asList(1,2).iterator();
        PreciseWindowIterator<LinkedList<Integer>, Integer> win = new PreciseWindowIterator<LinkedList<Integer>, Integer>(iter, 2, new LinkedListFactory<Integer>());
        win.hasNext();
        win.next();
    }

    @Test
    public void windowsAreInOrder() {
        final Iterator<O> iter = Iterations.iterator(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        final PreciseWindowIterator<List<O>, O> win = new PreciseWindowIterator<List<O>, O>(iter, 2, LIST_FACTORY);
        final List<O> got = new ArrayList<O>();
        got.addAll(win.next());
        got.addAll(win.next());
        Assert.assertEquals(Arrays.asList(O.ONE, O.ANOTHER, O.ANOTHER, O.YET_ANOTHER), got);
    }

    @Test
    public void windowsHaveTheCorrectSize() {
        final Iterator<O> iter = Iterations.iterator(O.ONE, O.ANOTHER);
        final PreciseWindowIterator<List<O>, O> win = new PreciseWindowIterator<List<O>, O>(iter, 2, LIST_FACTORY);
        Assert.assertEquals(2, win.next().size());
    }
}
