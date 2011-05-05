package net.emaze.dysfunctional.time;

/**
 *
 * @author rferranti
 */
public class RealTimeStrategy implements TimeStrategy {

    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            throw new SleepInterruptedException(ex);
        }
    }

    public static class SleepInterruptedException extends IllegalStateException {

        public SleepInterruptedException(InterruptedException cause) {
            super("sleep interrupted", cause);
        }
    }
}
