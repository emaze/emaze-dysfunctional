package net.emaze.dysfunctional.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.Dys.Consumers;
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

    @Test(expected = UnsupportedOperationException.class)
    public void cannotRemoveFromIterator() {
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        TakeWhileIterator<Integer> twi = new TakeWhileIterator<Integer>(ints.iterator(), new Always<Integer>());
        twi.next();
        twi.remove();
    }

    @Test
    public void afterTakingAllNeededStops() {
        Iterator<Integer> srcIter = Arrays.asList(1, 2, 3, 4, 5).iterator();
        TakeWhileIterator<Integer> twi = new TakeWhileIterator<Integer>(srcIter, new UntilCount<Integer>(1));
        Consumers.all(twi);
        Assert.assertTrue(srcIter.hasNext());
    }
    
    @Test(expected=NoSuchElementException.class)
    public void consumingEmptyIteratorYieldsException() {
        Iterator<Integer> srcIter = Arrays.<Integer>asList().iterator();
        TakeWhileIterator<Integer> twi = new TakeWhileIterator<Integer>(srcIter, new Always<Integer>());
        twi.next();
    }
    
    @Test(expected=NoSuchElementException.class)
    public void consumingIteratorAfterPredicateYieldsException() {
        Iterator<Integer> srcIter = Arrays.<Integer>asList(1,2).iterator();
        TakeWhileIterator<Integer> twi = new TakeWhileIterator<Integer>(srcIter, new UntilCount<Integer>(1));
        twi.next();
        twi.next();
    }

    @Test(expected=IllegalArgumentException.class)
    public void creatingWithNullIteratorYieldsException(){
        new TakeWhileIterator<Integer>(null, new UntilCount<Integer>(1));
    }
    @Test(expected=IllegalArgumentException.class)
    public void creatingWithNullPredicateYieldsException(){
        final Iterator<Integer> iter = Arrays.<Integer>asList(1,2).iterator();
        new TakeWhileIterator<Integer>(iter, null);
    }

}
