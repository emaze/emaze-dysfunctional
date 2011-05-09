package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
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
                sleeper.perform(10l, TimeUnit.SECONDS);
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
                sleeper.perform(expectedMillis, TimeUnit.MILLISECONDS);
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
        BinaryAction action = sleeper;
        action.perform(1l, new Object());
    }

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeForDurationInErasureYieldsException() {
        BinaryAction action = sleeper;
        action.perform(new Object(), TimeUnit.MILLISECONDS);
    }
}
