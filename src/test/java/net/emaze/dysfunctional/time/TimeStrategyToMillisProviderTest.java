package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TimeStrategyToMillisProviderTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullStrategyYieldsException() {
        new TimeStrategyToMillisProvider(null);
    }

    @Test
    public void canConvertEpoch() {
        final TimeStrategyToMillisProvider provider = new TimeStrategyToMillisProvider(new WarpingTimeStrategy(0));
        Assert.assertEquals(0l, provider.provide().longValue());
    }

    @Test
    public void canConvertDifferentResolution() {
        final TimeStrategyToMillisProvider provider = new TimeStrategyToMillisProvider(new TimeStrategy() {

            @Override
            public Pair<Long, TimeUnit> currentTime() {
                return Pair.of(1l, TimeUnit.HOURS);
            }

            @Override
            public void sleep(long duration, TimeUnit unit) {
            }
        });

        Assert.assertEquals(3600 * 1000l, provider.provide().longValue());
    }
}
