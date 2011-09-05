package net.emaze.dysfunctional.dispatching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import junit.framework.Assert;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TransformingTest {

    @Test
    public void canTransformAnIterable() {
        List<Integer> source = Arrays.asList(1, 2, 3);
        Iterator<Integer> got = Transforming.transform(source, new Identity<Integer>());
        Assert.assertEquals(source, Consumers.all(got));
    }

    @Test
    public void canTransformAnIterator() {
        List<Integer> source = Arrays.asList(1, 2, 3);
        Iterator<Integer> got = Transforming.transform(source.iterator(), new Identity<Integer>());
        Assert.assertEquals(source, Consumers.all(got));
    }

    @Test
    public void canTransformAnArray() {
        Integer[] source = new Integer[]{1, 2, 3};
        Iterator<Integer> got = Transforming.transform(source, new Identity<Integer>());
        Assert.assertEquals(Arrays.asList(source), Consumers.all(got));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallTransformWithNullIterable() {
        final Iterable<Object> iterable = null;
        Transforming.transform(iterable, new Identity<Object>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallTransformWithNullDelegate() {
        Transforming.transform(new ArrayList(), null);
    }
}
