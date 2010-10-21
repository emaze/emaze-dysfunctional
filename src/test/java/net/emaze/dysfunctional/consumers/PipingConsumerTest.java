package net.emaze.dysfunctional.consumers;

import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import net.emaze.dysfunctional.iterations.StringOutputIterator;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PipingConsumerTest {

    @Test
    public void testThatCanCosume() {
        final List<String> input = Arrays.asList("1","2","3","4","5");
        final StringOutputIterator output = new StringOutputIterator();
        final PipingConsumer<String> pipe = new PipingConsumer<String>(output);
        pipe.consume(input.iterator());
        Assert.assertEquals("12345", output.toString());
    }

}