package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TimeStrategyToMillisSupplierTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullStrategyYieldsException() {
        new TimeStrategyToMillisSupplier(null);
    }

    @Test
    public void canConvertEpoch() {
        final TimeStrategyToMillisSupplier supplier = new TimeStrategyToMillisSupplier(new WarpingTimeStrategy(new WarpingKnobs()));
        Assert.assertEquals(0l, supplier.get().longValue());
    }

    @Test
    public void canConvertDifferentResolution() {
        final TimeStrategyToMillisSupplier supplier = new TimeStrategyToMillisSupplier(new TimeStrategy() {

            @Override
            public Pair<Long, TimeUnit> currentTime() {
                return Pair.of(1l, TimeUnit.HOURS);
            }

            @Override
            public void sleep(long duration, TimeUnit unit) {
            }
        });

        Assert.assertEquals(3600 * 1000l, supplier.get().longValue());
    }
}
