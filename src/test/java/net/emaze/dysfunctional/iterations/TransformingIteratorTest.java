package net.emaze.dysfunctional.iterations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;
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
        new TransformingIterator<Object, Object>(null, UnaryOperator.identity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingTransformingIteratorWithNullDelegateYieldsException() {
        new TransformingIterator<Object, Object>(Collections.emptyList().iterator(), null);
    }

    @Test
    public void removingFromTransformingIteratorRemovesFromNestedIterator() {
        List<Integer> bucket = new ArrayList<Integer>();
        bucket.add(1);
        TransformingIterator<Integer, Integer> t = new TransformingIterator<Integer, Integer>(bucket.iterator(), UnaryOperator.identity());
        t.next();
        t.remove();
        Assert.assertEquals(0, bucket.size());
    }

    @Test
    public void transIterHasNoNextWhenInnerIteratorHasNoNext() {
        TransformingIterator<Object, Object> t = new TransformingIterator<Object, Object>(Collections.emptyList().iterator(), UnaryOperator.identity());
        Assert.assertFalse(t.hasNext());
    }

    @Test
    public void transIterHasNextWhenInnerIteratorHasNext() {
        List<Integer> bucket = Arrays.asList(1);
        TransformingIterator<Integer, Integer> t = new TransformingIterator<Integer, Integer>(bucket.iterator(), UnaryOperator.identity());
        Assert.assertTrue(t.hasNext());
    }
    
    @Test
    public void nextTransformsElement() {
        List<Integer> bucket = Arrays.asList(1);
        TransformingIterator<Integer, String> t = new TransformingIterator<>(bucket.iterator(), new ToStringTransformer<Integer>());
        Assert.assertEquals("1", t.next());
    }
}
