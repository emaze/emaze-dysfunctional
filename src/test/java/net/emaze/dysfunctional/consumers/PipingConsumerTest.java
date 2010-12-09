package net.emaze.dysfunctional.consumers;

import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PipingConsumerTest {

    @Test
    public void testThatCanConsume() {
        final List<String> input = Arrays.asList("1","2","3","4","5");
        final StringOutputIterator output = new StringOutputIterator();
        final PipingConsumer<String> pipe = new PipingConsumer<String>(output);
        pipe.consume(input.iterator());
        Assert.assertEquals("12345", output.toString());
    }

    @Test
    public void testThatCanConsumeEmptyList() {
        final List<String> input = Arrays.<String>asList();
        final StringOutputIterator output = new StringOutputIterator();
        final PipingConsumer<String> pipe = new PipingConsumer<String>(output);
        pipe.consume(input.iterator());
        Assert.assertEquals("", output.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void consumingNullIteratorYieldException() {
        final StringOutputIterator output = new StringOutputIterator();
        new PipingConsumer<String>(output).consume(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullOutputIteratorYieldException() {
        new PipingConsumer<String>(null);
    }
}