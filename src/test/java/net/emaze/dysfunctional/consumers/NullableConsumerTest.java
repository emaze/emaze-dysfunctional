package net.emaze.dysfunctional.consumers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.delegates.Always;
import net.emaze.dysfunctional.delegates.Never;
import org.junit.Assert;
import org.junit.Test;

public class NullableConsumerTest {

    @Test
    public void testFirstMatchIsReturned() {
        List values = Arrays.asList(1,2,3);
        Consumer instance = new NullableConsumer(new Always());
        Assert.assertEquals(1, instance.consume(values.iterator()));
    }

    @Test
    public void testEmptyIteratorYieldsNull() {
        Consumer instance = new NullableConsumer(new Always());
        Assert.assertNull(instance.consume(Collections.EMPTY_LIST.iterator()));
    }

    @Test
    public void testNoMatchYieldsNull() {
        List values = Arrays.asList(1,2,3);
        Consumer instance = new NullableConsumer(new Never());
        Assert.assertNull(instance.consume(values.iterator()));
    }
}