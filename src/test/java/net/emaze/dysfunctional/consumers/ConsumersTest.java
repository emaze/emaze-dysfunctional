package net.emaze.dysfunctional.consumers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConsumersTest {

    private List<Integer> list = Arrays.asList(1, 2);
    private Integer[] array = new Integer[]{1, 2};

    @Test
    public void canFetchFirstFromIterator() {
        Assert.assertEquals(Integer.valueOf(1), Consumers.first(list.iterator()));
    }

    @Test
    public void canFetchFirstFromIterable() {
        Assert.assertEquals(Integer.valueOf(1), Consumers.first(list));
    }

    @Test
    public void canFetchFirstFromArray() {
        Assert.assertEquals(Integer.valueOf(1), Consumers.first(array));
    }

    @Test
    public void canFetchAllFromIterator() {
        List<Integer> got = Consumers.all(list.iterator());
        Assert.assertEquals(list, got);
    }

    @Test
    public void canFetchAllFromIterable() {
        List<Integer> got = Consumers.all(list);
        Assert.assertEquals(list, got);
    }

    @Test
    public void canFetchAllFromArray() {
        List<Integer> got = Consumers.all(array);
        Assert.assertEquals(list, got);
    }
    @Test
    public void canFetchAllFromIteratorWithFactory() {
        List<Integer> got = Consumers.<ArrayList<Integer>, Integer, ArrayListFactory<Integer>>all(list.iterator(), new ArrayListFactory<Integer>());
        Assert.assertEquals(list, got);
    }

    @Test
    public void canFetchAllFromIterableWithFactory() {
        List<Integer> got = Consumers.<ArrayList<Integer>, Integer, ArrayListFactory<Integer>>all(list, new ArrayListFactory<Integer>());
        Assert.assertEquals(list, got);
    }

    @Test
    public void canFetchAllFromArrayWithFactory() {
        List<Integer> got = Consumers.<ArrayList<Integer>, Integer, ArrayListFactory<Integer>>all(array, new ArrayListFactory<Integer>());
        Assert.assertEquals(list, got);
    }

    @Test
    public void canFetchLastFromIterator() {
        Assert.assertEquals(Integer.valueOf(2), Consumers.last(list.iterator()));
    }

    @Test
    public void canFetchLastFromIterable() {
        Assert.assertEquals(Integer.valueOf(2), Consumers.last(list));
    }

    @Test
    public void canFetchLastFromArray() {
        Assert.assertEquals(Integer.valueOf(2), Consumers.last(array));
    }

    @Test
    public void canPipeFromIterator() {
        List<String> in = Arrays.asList("1", "2", "3");
        StringOutputIterator output = new StringOutputIterator();
        Consumers.pipe(in.iterator(), output);
        Assert.assertEquals("123", output.toString());
    }

    @Test
    public void canPipeFromIterable() {
        List<String> in = Arrays.asList("1", "2", "3");
        StringOutputIterator output = new StringOutputIterator();
        Consumers.pipe(in, output);
        Assert.assertEquals("123", output.toString());
    }

    @Test
    public void canPipeFromArray() {
        String[] in = new String[]{"1", "2", "3"};
        StringOutputIterator output = new StringOutputIterator();
        Consumers.pipe(in, output);
        Assert.assertEquals("123", output.toString());
    }
}
