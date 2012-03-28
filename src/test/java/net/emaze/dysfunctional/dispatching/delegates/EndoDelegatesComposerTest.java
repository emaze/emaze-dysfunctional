package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Iterator;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class EndoDelegatesComposerTest {

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullIteratorYieldsException() {
        new EndoDelegatesComposer<O>().perform(null);
    }

    @Test
    public void callingYieldsComposedDelegate() {
        final Iterator<Delegate<String, String>> delegates = Iterations.<Delegate<String, String>>iterator(new EndoDelegate("f"), new EndoDelegate("g"));
        final Delegate<String, String> delegate = new EndoDelegatesComposer<String>().perform(delegates);
        final String evaluated = delegate.perform("x");
        Assert.assertEquals("f(g(x))", evaluated);
    }
    
    private static class EndoDelegate implements Delegate<String, String> {

        private final String fn;

        public EndoDelegate(String fn) {
            this.fn = fn;
        }
        
        @Override
        public String perform(String argument) {
            return String.format("%s(%s)", fn, argument);
        }
    }
}