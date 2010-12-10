package net.emaze.dysfunctional.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.delegates.Always;
import net.emaze.dysfunctional.delegates.Never;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TakeWhileIteratorTest {

    @Test
    public void canTakeEveryObject() {
        List<Integer> ints = Arrays.asList(1);
        TakeWhileIterator<Integer> twi = new TakeWhileIterator<Integer>(ints.iterator(), new Always<Integer>());
        twi.next();
    }

    @Test
    public void neverMatchingHasNoNext() {
        List<Integer> ints = Arrays.asList(1);
        TakeWhileIterator<Integer> twi = new TakeWhileIterator<Integer>(ints.iterator(), new Never<Integer>());
        Assert.assertFalse(twi.hasNext());
    }

    @Test
    public void canRemoveFromIterator(){
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        TakeWhileIterator<Integer> twi = new TakeWhileIterator<Integer>(ints.iterator(), new Always<Integer>());
        twi.next();
        twi.remove();
        Assert.assertEquals(Collections.<Integer>emptyList(), ints);
    }
}
