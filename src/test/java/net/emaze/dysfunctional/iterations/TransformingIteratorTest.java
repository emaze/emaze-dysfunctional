package net.emaze.dysfunctional.iterations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.strings.ToStringTransformer;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TransformingIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingTransformingIteratorWithNullIteratorYieldsException() {
        new TransformingIterator<Object, Object>(null, new Identity<Object>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingTransformingIteratorWithNullDelegateYieldsException() {
        new TransformingIterator<Object, Object>(Collections.emptyList().iterator(), null);
    }

    @Test
    public void removingFromTransformingIteratorRemovesFromNestedIterator() {
        List<Integer> bucket = new ArrayList<Integer>();
        bucket.add(1);
        TransformingIterator<Integer, Integer> t = new TransformingIterator<Integer, Integer>(bucket.iterator(), new Identity<Integer>());
        t.next();
        t.remove();
        Assert.assertEquals(0, bucket.size());
    }

    @Test
    public void transIterHasNoNextWhenInnerIteratorHasNoNext() {
        TransformingIterator<Object, Object> t = new TransformingIterator<Object, Object>(Collections.emptyList().iterator(), new Identity<Object>());
        Assert.assertFalse(t.hasNext());
    }

    @Test
    public void transIterHasNextWhenInnerIteratorHasNext() {
        List<Integer> bucket = Arrays.asList(1);
        TransformingIterator<Integer, Integer> t = new TransformingIterator<Integer, Integer>(bucket.iterator(), new Identity<Integer>());
        Assert.assertTrue(t.hasNext());
    }
    
    @Test
    public void nextTransformsElement() {
        List<Integer> bucket = Arrays.asList(1);
        TransformingIterator<String, Integer> t = new TransformingIterator<String, Integer>(bucket.iterator(), new ToStringTransformer<Integer>());
        Assert.assertEquals("1", t.next());
    }
}
