package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ReadOnlyIteratorTest {

    @Test(expected = UnsupportedOperationException.class)
    public void removingFromReadOnlyIteratorYieldsException() {
        final ReadOnlyIterator<O> rit = new ReadOnlyIterator<O>() {

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public O next() {
                return O.ONE;
            }
        };
        rit.remove();
    }
}
