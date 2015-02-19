package net.emaze.dysfunctional.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Maps;
import net.emaze.dysfunctional.testing.O;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

public class MapToPairsTest {

    @Test(expected = IllegalArgumentException.class)
    public void transformingNullMapYieldsException() {
        new MapToPairs<O, O>().apply(null);
    }

    @Test
    public void canTransformToAnIterator() {
        final Map<String, String> m = Maps.<String, String>tree().
                add("a", "1").
                add("b", "2").
                toMap();
        final Iterator<Pair<String, String>> got = new MapToPairs<String, String>().apply(m);
        Assert.assertEquals(Arrays.asList(Pair.of("a", "1"), Pair.of("b", "2")), Consumers.all(got));
    }
}