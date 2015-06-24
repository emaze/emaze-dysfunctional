package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Iterator;
import java.util.function.Function;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class UnaryOperatorsComposerTest {

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullIteratorYieldsException() {
        new UnaryOperatorsComposer<O>().apply(null);
    }

    @Test
    public void callingYieldsComposedDelegate() {
        final Iterator<Function<String, String>> delegates = Iterations.<Function<String, String>>iterator(new UnaryOperator("f"), new UnaryOperator("g"));
        final Function<String, String> function = new UnaryOperatorsComposer<String>().apply(delegates);
        final String evaluated = function.apply("x");
        Assert.assertEquals("f(g(x))", evaluated);
    }

    private static class UnaryOperator implements Function<String, String> {

        private final String fn;

        public UnaryOperator(String fn) {
            this.fn = fn;
        }

        @Override
        public String apply(String argument) {
            return String.format("%s(%s)", fn, argument);
        }
    }
}
