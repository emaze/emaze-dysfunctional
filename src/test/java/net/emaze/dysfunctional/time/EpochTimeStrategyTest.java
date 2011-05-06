package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.options.Box;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class EpochTimeStrategyTest {

    private final EpochTimeStrategy strategy = new EpochTimeStrategy();

    @Test
    public void interruptingSleepYieldsException() throws InterruptedException {
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
        thread.interrupt();
        thread.join();
        Assert.assertFalse(shouldFail.getContent());
    }

    @Test
    public void uninterruptedSleepWaitsAtLeastTheDuration() throws InterruptedException {
        final long expectedMillis = 10;
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                strategy.sleep(expectedMillis, TimeUnit.MILLISECONDS);
            }
        });
        final long start = System.currentTimeMillis();
        thread.start();
        thread.join();
        long duration = System.currentTimeMillis() - start;
        Assert.assertTrue(duration >= expectedMillis);
    }

    @Test
    public void epochTimeUnitIsMilliSeconds() {
        final TimeUnit got = strategy.currentTime().second();
        Assert.assertEquals(TimeUnit.MILLISECONDS, got);
    }
}
