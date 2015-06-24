package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.Iterations;
import org.junit.Assert;
import org.junit.Test;

public class InterposeStringsTest {

    @Test(expected = IllegalArgumentException.class)
    public void interposingNullIteratorOfValuesYieldsException() {
        Iterator<String> values = null;
        Iterator<String> separators = Iterations.iterator("-");
        new InterposeStrings<String, String>().apply(values, separators);
    }

    @Test(expected = IllegalArgumentException.class)
    public void interposingNullIteratorOfSeparatorsYieldsException() {
        Iterator<String> values = Iterations.iterator("1", "2");
        Iterator<String> separators = null;
        new InterposeStrings<String, String>().apply(values, separators);
    }

    @Test
    public void canInterposeValuesAndSeparators() {
        Iterator<String> values = Iterations.iterator("1", "2");
        Iterator<String> separators = Iterations.iterator("-");
        String got = new InterposeStrings<String, String>().apply(values, separators);
        Assert.assertEquals("1-2", got);
    }
}