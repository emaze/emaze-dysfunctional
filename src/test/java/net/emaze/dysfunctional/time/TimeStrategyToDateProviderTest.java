package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Test;

public class TimeStrategyToDateProviderTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotPassANullArgumentToTheConstructor() {
        new TimeStrategyToDateProvider(null);
    }

    @Test
    public void canAdapt() {
        new TimeStrategyToDateProvider(new StubTimeStrategy()).provide();
    }

    private static class StubTimeStrategy implements TimeStrategy {

        @Override
        public Pair<Long, TimeUnit> currentTime() {
            return Pair.of(0L, TimeUnit.DAYS);
        }

        @Override
        public void sleep(long duration, TimeUnit unit) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
