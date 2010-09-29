package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.multiplexing.InterposingIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class InterleavingIteratorTest {

    @Test
    public void canInterleaveStrings() {
        final List<String> values = Arrays.asList("1", "2", "3");
        final List<String> separators = Arrays.asList("a", "b");
        final InterposingIterator<String> iter = new InterposingIterator<String>(values.iterator(), separators.iterator());
        final List<String> got = new ArrayList<String>();
        while(iter.hasNext()){
            got.add(iter.next());
        }
        final List<String> expected = Arrays.asList("1", "a", "2", "b", "3");
        Assert.assertEquals(expected, got);
    }

}