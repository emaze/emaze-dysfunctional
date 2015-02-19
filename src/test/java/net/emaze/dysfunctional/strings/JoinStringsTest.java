package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import junit.framework.Assert;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

public class JoinStringsTest {

    @Test(expected = IllegalArgumentException.class)
    public void joiningNullIteratorYieldsException() {
        new JoinStrings<O>().apply(null);
    }

    @Test
    public void canJoinMultipleStrings() {
        final Iterator<String> iterator = Iterations.iterator("1", "2");
        final String got = new JoinStrings<String>().apply(iterator);
        Assert.assertEquals("12", got);
    }
}