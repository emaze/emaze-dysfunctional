package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TimeToDateTest {

    private TimeToDate instance = new TimeToDate();

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotPassANullArgument() {
        instance.apply(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotPassAPairWithNullFirst() {
        instance.apply(Pair.of((Long) null, TimeUnit.DAYS));
    }

    @Test
    public void canConvertTimeToDate() {
        Assert.assertNotNull(instance.apply(Pair.of((long) 1, TimeUnit.DAYS)));
    }
}
