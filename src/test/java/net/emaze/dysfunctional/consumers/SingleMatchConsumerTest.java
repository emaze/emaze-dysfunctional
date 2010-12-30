package net.emaze.dysfunctional.consumers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.delegates.Always;
import net.emaze.dysfunctional.delegates.Never;
import net.emaze.dysfunctional.delegates.Nth;
import org.junit.Assert;
import org.junit.Test;

public class SingleMatchConsumerTest {

    @Test
    public void testSingleMatchYieldsMatchingElementInFirstPosition() {
        List values = Arrays.asList(1,2,3);
        Consumer instance = new SingleMatchConsumer(new Nth(1));
        Assert.assertEquals(1, instance.consume(values.iterator()));
    }

    @Test
    public void testSingleMatchYieldsMatchingElementInLastPosition() {
        List values = Arrays.asList(1,2,3);
        Consumer instance = new SingleMatchConsumer(new Nth(3));
        Assert.assertEquals(3, instance.consume(values.iterator()));
    }

    @Test(expected=IllegalStateException.class)
    public void testMultipleMatchThrows() {
        List values = Arrays.asList(1,2,3);
        Consumer instance = new SingleMatchConsumer(new Always());
        instance.consume(values.iterator());
    }

    @Test(expected=IllegalStateException.class)
    public void testNoMatchThrows() {
        List values = Arrays.asList(1,2,3);
        Consumer instance = new SingleMatchConsumer(new Never());
        instance.consume(values.iterator());
    }

    @Test(expected=IllegalStateException.class)
    public void testEmptyIteratorThrows() {
        Consumer instance = new SingleMatchConsumer(new Always());
        instance.consume(Collections.EMPTY_LIST.iterator());
    }

}