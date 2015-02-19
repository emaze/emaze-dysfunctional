package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import java.util.Iterator;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class EndoDelegatesComposerTest {

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullIteratorYieldsException() {
        new EndoDelegatesComposer<O>().apply(null);
    }

    @Test
    public void callingYieldsComposedDelegate() {
        final Iterator<Function<String, String>> delegates = Iterations.<Function<String, String>>iterator(new EndoDelegate("f"), new EndoDelegate("g"));
        final Function<String, String> delegate = new EndoDelegatesComposer<String>().apply(delegates);
        final String evaluated = delegate.apply("x");
        Assert.assertEquals("f(g(x))", evaluated);
    }
    
    private static class EndoDelegate implements Function<String, String> {

        private final String fn;

        public EndoDelegate(String fn) {
            this.fn = fn;
        }
        
        @Override
        public String apply(String argument) {
            return String.format("%s(%s)", fn, argument);
        }
    }
}