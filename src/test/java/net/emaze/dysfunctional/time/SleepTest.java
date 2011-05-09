package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class SleepTest {

    final long ICE_AGE = 0l;
    final WarpingTimeStrategy clock = new WarpingTimeStrategy(ICE_AGE);

    /**
     * when you don't want to wait for something just leap forward in the future.
     */
    @Test
    public void sleepMovesTimeToTheFuture() {
        new Sleep(clock).perform(1l, TimeUnit.MILLISECONDS);
        final Pair<Long, TimeUnit> currentTime = clock.currentTime();
        final long currentTimeInMillis = currentTime.second().toMillis(currentTime.first());
        Assert.assertEquals(ICE_AGE + 1, currentTimeInMillis);
    }
    

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeForTimeUnitInErasureYieldsException() {
        BinaryAction action = new Sleep(clock);
        action.perform(1l, new Object());
    }       
    
    @Test(expected = ClassCastException.class)
    public void passingWrongTypeForDurationInErasureYieldsException() {
        BinaryAction action = new Sleep(clock);
        action.perform(new Object(), TimeUnit.MILLISECONDS);
    }       
}
