package net.emaze.dysfunctional.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.logic.Always;
import net.emaze.dysfunctional.logic.Never;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DropWhileIteratorTest {
    @Test
    public void canTakeEveryObject() {
        List<Integer> ints = Arrays.asList(1);
        DropWhileIterator<Integer> twi = new DropWhileIterator<Integer>(ints.iterator(), new Never<Integer>());
        twi.next();
    }

    @Test
    public void neverMatchingHasNoNext() {
        List<Integer> ints = Arrays.asList(1);
        DropWhileIterator<Integer> twi = new DropWhileIterator<Integer>(ints.iterator(), new Always<Integer>());
        Assert.assertFalse(twi.hasNext());
    }

    @Test
    public void canRemoveFromIterator(){
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        DropWhileIterator<Integer> twi = new DropWhileIterator<Integer>(ints.iterator(), new Never<Integer>());
        twi.next();
        twi.remove();
        Assert.assertEquals(Collections.<Integer>emptyList(), ints);
    }
}