package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import junit.framework.Assert;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

public class JoinStringsTest {

    @Test(expected = IllegalArgumentException.class)
    public void joiningNullIteratorYieldsException() {
        new JoinStrings<O>().perform(null);
    }

    @Test
    public void canJoinMultipleStrings() {
        final Iterator<String> iterator = Iterations.iterator("1", "2");
        final String got = new JoinStrings<String>().perform(iterator);
        Assert.assertEquals("12", got);
    }
}