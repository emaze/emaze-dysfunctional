package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import java.util.function.BiConsumer;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class SleepAtLeastTest {

    private final WarpingTimeStrategy strategy = new WarpingTimeStrategy(new WarpingKnobs());

    @Test
    public void canSleep() {
        final SleepAtLeast sleeper = new SleepAtLeast(strategy);
        sleeper.accept(1l, TimeUnit.DAYS);
    }

    @Test
    public void interruptingDuringSleepForcesResleepUntilDuration() throws InterruptedException {
        final InterruptingEverySecondTimeStrategy interruptingStrategy = new InterruptingEverySecondTimeStrategy(0);
        final SleepAtLeast sleeper = new SleepAtLeast(interruptingStrategy);
        sleeper.accept(2l, TimeUnit.SECONDS);
        Assert.assertEquals(Pair.of(TimeUnit.SECONDS.toMillis(2l), TimeUnit.MILLISECONDS), interruptingStrategy.currentTime());
    }
    
    @Test
    public void interruptingDuringSleepDoesNotThrow() throws InterruptedException {
        final InterruptingEverySecondTimeStrategy interruptingStrategy = new InterruptingEverySecondTimeStrategy(0);
        final SleepAtLeast sleeper = new SleepAtLeast(interruptingStrategy);
        sleeper.accept(1l, TimeUnit.SECONDS);
        Assert.assertEquals(Pair.of(TimeUnit.SECONDS.toMillis(1l), TimeUnit.MILLISECONDS), interruptingStrategy.currentTime());
    }
    
    @Test(expected = ClassCastException.class)
    public void passingWrongTypeForTimeUnitInErasureYieldsException() {
        final InterruptingEverySecondTimeStrategy interruptingStrategy = new InterruptingEverySecondTimeStrategy(0);
        BiConsumer action = new SleepAtLeast(interruptingStrategy);        
        action.accept(1l, new Object());
    }

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeForDurationInErasureYieldsException() {
        final InterruptingEverySecondTimeStrategy interruptingStrategy = new InterruptingEverySecondTimeStrategy(0);
        BiConsumer action = new SleepAtLeast(interruptingStrategy);        
        action.accept(new Object(), TimeUnit.MILLISECONDS);
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
