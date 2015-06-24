package net.emaze.dysfunctional.collections;

import java.util.Collections;
import java.util.Map.Entry;
import net.emaze.dysfunctional.testing.O;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

public class EntryToPairTest {

    @Test(expected = IllegalArgumentException.class)
    public void cannotTransformNullEntryToPair() {
        new EntryToPair<O, O>().apply(null);
    }

    @Test
    public void keyIsTransformedToFirst() {
        final Entry<String, String> entry = Collections.singletonMap("key", "value").entrySet().iterator().next();
        final Pair<String, String> got = new EntryToPair<String, String>().apply(entry);
        Assert.assertEquals(got.first(), entry.getKey());
    }

    @Test
    public void valueIsTransformedToSecond() {
        final Entry<String, String> entry = Collections.singletonMap("key", "value").entrySet().iterator().next();
        final Pair<String, String> got = new EntryToPair<String, String>().apply(entry);
        Assert.assertEquals(got.second(), entry.getValue());
    }
}