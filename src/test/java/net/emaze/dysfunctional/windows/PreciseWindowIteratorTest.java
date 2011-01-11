package net.emaze.dysfunctional.windows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PreciseWindowIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingPreciseWindowIteratorWithNullIteratorYieldsException() {
        new PreciseWindowIterator<Object>(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingPreciseWindowIteratorWithNonPositiveWindowSizeYieldsException() {
        new PreciseWindowIterator<Object>(Collections.emptyList().iterator(), 0);
    }

    @Test
    public void nonEmptyIterHasNext() {
        Iterator<Integer> iter = Arrays.asList(1).iterator();
        PreciseWindowIterator<Integer> win = new PreciseWindowIterator<Integer>(iter, 1);
        Assert.assertTrue(win.hasNext());
    }

    @Test
    public void emptyIterHasNoNext() {
        Iterator<Integer> iter = Arrays.<Integer>asList().iterator();
        PreciseWindowIterator<Integer> win = new PreciseWindowIterator<Integer>(iter, 1);
        Assert.assertFalse(win.hasNext());
    }
    
    @Test
    public void windowsAreInOrder() {
        Iterator<Integer> iter = Arrays.<Integer>asList(1,2,3).iterator();
        PreciseWindowIterator<Integer> win = new PreciseWindowIterator<Integer>(iter, 2);
        List<Integer> got = new ArrayList<Integer>();
        got.addAll(win.next());
        got.addAll(win.next());
        Assert.assertEquals(Arrays.asList(1,2,2,3), got);
    }
    
    @Test
    public void windowsHaveTheCorrectSize() {
        Iterator<Integer> iter = Arrays.<Integer>asList(1,2).iterator();
        PreciseWindowIterator<Integer> win = new PreciseWindowIterator<Integer>(iter, 2);
        Assert.assertEquals(2, win.next().size());
    }

}
