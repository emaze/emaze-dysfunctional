package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TimeStrategiesTest.InterruptedSleepYieldsException.class,
    TimeStrategiesTest.UninterruptedSleepWaitsForDuration.class,
    TimeStrategiesTest.Warping.class,
    TimeStrategiesTest.Resolutions.class
})
public class TimeStrategiesTest {

    @RunWith(Theories.class)
    public static class InterruptedSleepYieldsException {

        @DataPoint
        public static EpochTimeStrategy epochStrategy = new EpochTimeStrategy();
        @DataPoint
        public static HiResTimeStrategy hiResStrategy = new HiResTimeStrategy();

        @Theory
        public void interruptingSleepYieldsException(final TimeStrategy strategy) throws InterruptedException {
            final Box<Boolean> shouldFail = new Box<Boolean>();
            shouldFail.setContent(Boolean.FALSE);
            final Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    strategy.sleep(30, TimeUnit.SECONDS);
                    shouldFail.setContent(Boolean.TRUE);
                }
            });
            thread.start();
            thread.setUncaughtExceptionHandler(new DoNotLogOnUncaughtException());
            thread.interrupt();
            thread.join();
            Assert.assertFalse(shouldFail.getContent());
        }
    }

    @RunWith(Theories.class)
    public static class UninterruptedSleepWaitsForDuration {

        @DataPoint
        public static EpochTimeStrategy epochStrategy = new EpochTimeStrategy();
        @DataPoint
        public static HiResTimeStrategy hiResStrategy = new HiResTimeStrategy();
        @DataPoint
        public static WarpingTimeStrategy warpingStrategy = new WarpingTimeStrategy(0);

        @Theory
        public void uninterruptedSleepWaitsAtLeastTheDuration(final TimeStrategy strategy) throws InterruptedException {
            final long expectedMillis = 10;
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    strategy.sleep(expectedMillis, TimeUnit.MILLISECONDS);
                }
            });
            final Pair<Long, TimeUnit> start = strategy.currentTime();
            thread.start();
            thread.join();
            final Pair<Long, TimeUnit> stop = strategy.currentTime();
            final Long durationInMillis = stop.second().toMillis(stop.first() - start.first());
            Assert.assertTrue(durationInMillis >= expectedMillis);
        }
    }

    public static class Resolutions {

        @Test
        public void epochTimeUnitInCurrentTimeIsInMillis() {
            final TimeUnit got = new EpochTimeStrategy().currentTime().second();
            Assert.assertEquals(TimeUnit.MILLISECONDS, got);
        }

        @Test
        public void hiResTimeUnitInCurrentTimeIsInNanos() {
            final TimeUnit got = new HiResTimeStrategy().currentTime().second();
            Assert.assertEquals(TimeUnit.NANOSECONDS, got);
        }

        @Test
        public void warpingTimeUnitInCurrentTimeIsInMillis() {
            final TimeUnit got = new WarpingTimeStrategy(0l).currentTime().second();
            Assert.assertEquals(TimeUnit.MILLISECONDS, got);
        }
    }

    public static class Warping {

        @Test
        public void canWarpTimeForWarpingTimeStrategy() {
            final WarpingTimeStrategy wts = new WarpingTimeStrategy(0l);
            wts.warp(1, TimeUnit.MILLISECONDS);
            Assert.assertEquals(Pair.of(1l, TimeUnit.MILLISECONDS), wts.currentTime());
        }
    }

    public static class DoNotLogOnUncaughtException implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
        }
    }
}
