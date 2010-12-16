package net.emaze.dysfunctional.iterations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.delegates.Identity;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IterationsTest {

    @Test
    public void canMapAnIterable() {
        List<Integer> source = Arrays.asList(1, 2, 3);
        List<Integer> got = Iterations.map(source, new Identity<Integer>());
        Assert.assertEquals(source, got);
    }

    @Test
    public void canMapAnIterator() {
        List<Integer> source = Arrays.asList(1, 2, 3);
        List<Integer> got = Iterations.map(source.iterator(), new Identity<Integer>());
        Assert.assertEquals(source, got);
    }

    @Test
    public void canMapAnArray() {
        Integer[] source = new Integer[]{1, 2, 3};
        List<Integer> got = Iterations.map(source, new Identity<Integer>());
        Assert.assertEquals(Arrays.asList(source), got);
    }

    @Test
    public void canTransformAnIterable() {
        List<Integer> source = Arrays.asList(1, 2, 3);
        Iterator<Integer> got = Iterations.transform(source, new Identity<Integer>());
        Assert.assertEquals(source, Consumers.all(got));
    }

    @Test
    public void canTransformAnIterator() {
        List<Integer> source = Arrays.asList(1, 2, 3);
        Iterator<Integer> got = Iterations.transform(source.iterator(), new Identity<Integer>());
        Assert.assertEquals(source, Consumers.all(got));
    }

    @Test
    public void canTransformAnArray() {
        Integer[] source = new Integer[]{1, 2, 3};
        Iterator<Integer> got = Iterations.transform(source, new Identity<Integer>());
        Assert.assertEquals(Arrays.asList(source), Consumers.all(got));
    }

    @Test
    public void canCreateAOneTimeIterable() {
        Assert.assertNotNull(Iterations.oneTime(Arrays.asList(1).iterator()));
    }
}
