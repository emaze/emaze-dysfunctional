package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
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
}
