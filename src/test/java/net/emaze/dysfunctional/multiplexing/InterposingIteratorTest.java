package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
import java.util.Iterator;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class InterposingIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingInterposingIterWithNullValuesYieldsException() {
        new InterposingIterator<Integer>(null, Arrays.asList(1).iterator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingInterposingIterWithNullSeparatorsYieldsException() {
        new InterposingIterator<Integer>(Arrays.asList(1).iterator(), null);
    }

    @Test(expected = IllegalStateException.class)
    public void removingFromIteratorYieldsException() {
        Iterator<Integer> iter = new InterposingIterator<Integer>(Arrays.asList(1).iterator(), Arrays.asList(1).iterator());
        iter.next();
        iter.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void tooSmallSeparatorIteratorYieldsToException() {
        Iterator<Integer> iter = new InterposingIterator<Integer>(Arrays.asList(1).iterator(), Arrays.<Integer>asList().iterator());
        iter.next();
        iter.next();
    }
}
