package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class SleepAtLeastTest {

    private final WarpingTimeStrategy strategy = new WarpingTimeStrategy(0l);

    @Test
    public void canSleep() {
        final SleepAtLeast sleeper = new SleepAtLeast(strategy);
        sleeper.perform(1l, TimeUnit.DAYS);
    }

    @Test
    public void interruptingDuringSleepForcesResleepUntilDuration() throws InterruptedException {
        final InterruptingEverySecondTimeStrategy interruptingStrategy = new InterruptingEverySecondTimeStrategy(0);
        final SleepAtLeast sleeper = new SleepAtLeast(interruptingStrategy);
        sleeper.perform(2l, TimeUnit.SECONDS);
        Assert.assertEquals(Pair.of(TimeUnit.SECONDS.toMillis(2l), TimeUnit.MILLISECONDS), interruptingStrategy.currentTime());
    }
    
    @Test
    public void interruptingDuringSleepDoesNotThrow() throws InterruptedException {
        final InterruptingEverySecondTimeStrategy interruptingStrategy = new InterruptingEverySecondTimeStrategy(0);
        final SleepAtLeast sleeper = new SleepAtLeast(interruptingStrategy);
        sleeper.perform(1l, TimeUnit.SECONDS);
        Assert.assertEquals(Pair.of(TimeUnit.SECONDS.toMillis(1l), TimeUnit.MILLISECONDS), interruptingStrategy.currentTime());
    }
    
    @Test(expected = ClassCastException.class)
    public void passingWrongTypeForTimeUnitInErasureYieldsException() {
        final InterruptingEverySecondTimeStrategy interruptingStrategy = new InterruptingEverySecondTimeStrategy(0);
        BinaryAction action = new SleepAtLeast(interruptingStrategy);        
        action.perform(1l, new Object());
    }

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeForDurationInErasureYieldsException() {
        final InterruptingEverySecondTimeStrategy interruptingStrategy = new InterruptingEverySecondTimeStrategy(0);
        BinaryAction action = new SleepAtLeast(interruptingStrategy);        
        action.perform(new Object(), TimeUnit.MILLISECONDS);
    }    

    public static class InterruptingEverySecondTimeStrategy implements TimeStrategy {

        public long now;

        public InterruptingEverySecondTimeStrategy(long now) {
            this.now = now;
        }

        @Override
        public Pair<Long, TimeUnit> currentTime() {
            return Pair.of(now, TimeUnit.MILLISECONDS);
        }

        @Override
        public void sleep(long duration, TimeUnit unit) {
            now+= TimeUnit.SECONDS.toMillis(1);
            throw new SleepInterruptedException(new InterruptedException("forged-exception"));
        }
    }
}
