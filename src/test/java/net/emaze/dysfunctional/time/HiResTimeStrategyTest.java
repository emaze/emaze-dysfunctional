package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.options.Box;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class HiResTimeStrategyTest {
    private final TimeStrategy strategy = new HiResTimeStrategy();

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
}
