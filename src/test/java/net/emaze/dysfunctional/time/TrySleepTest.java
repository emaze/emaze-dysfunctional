package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TrySleepTest {

    private final TrySleep sleeper = new TrySleep(new EpochTimeStrategy());

    @Test
    public void interruptingTrySleepDoesNotYieldException() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                sleeper.accept(10l, TimeUnit.SECONDS);
            }
        });
        thread.start();
        thread.interrupt();
        thread.join();
        Assert.assertEquals(0, thread.getStackTrace().length);
    }

    @Test
    public void uninterruptedSleepWaitsAtLeastTheDuration() throws InterruptedException {
        final long expectedMillis = 10;
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                sleeper.accept(expectedMillis, TimeUnit.MILLISECONDS);
            }
        });
        final long start = System.currentTimeMillis();
        thread.start();
        thread.join();
        long duration = System.currentTimeMillis() - start;
        Assert.assertTrue(duration >= expectedMillis);
    }

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeForTimeUnitInErasureYieldsException() {
        BiConsumer consumer = sleeper;
        consumer.accept(1l, new Object());
    }

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeForDurationInErasureYieldsException() {
        BiConsumer consumer = sleeper;
        consumer.accept(new Object(), TimeUnit.MILLISECONDS);
    }
}
