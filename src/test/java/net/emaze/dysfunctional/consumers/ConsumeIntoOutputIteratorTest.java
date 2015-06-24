package net.emaze.dysfunctional.consumers;

import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import java.util.function.Function;
import net.emaze.dysfunctional.output.StringOutputIterator;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConsumeIntoOutputIteratorTest {

    @Test
    public void testThatCanConsume() {
        final List<String> input = Arrays.asList("1","2","3","4","5");
        final StringOutputIterator output = new StringOutputIterator();
        final ConsumeIntoOutputIterator<String> pipe = new ConsumeIntoOutputIterator<String>(output);
        pipe.apply(input.iterator());
        Assert.assertEquals("12345", output.toString());
    }

    @Test
    public void testThatCanConsumeEmptyList() {
        final List<String> input = Arrays.<String>asList();
        final StringOutputIterator output = new StringOutputIterator();
        final ConsumeIntoOutputIterator<String> pipe = new ConsumeIntoOutputIterator<String>(output);
        pipe.apply(input.iterator());
        Assert.assertEquals("", output.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void consumingNullIteratorYieldException() {
        final StringOutputIterator output = new StringOutputIterator();
        new ConsumeIntoOutputIterator<String>(output).apply(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullOutputIteratorYieldException() {
        new ConsumeIntoOutputIterator<String>(null);
    }
    
    @Test(expected = ClassCastException.class)
    public void consumingFromErasureWithWrongTypeYieldsException() {
        final StringOutputIterator output = new StringOutputIterator();
        Function pipe = new ConsumeIntoOutputIterator(output);
        pipe.apply(new Object());
    }    
}