package net.emaze.dysfunctional.time;

/**
 *
 * @author rferranti
 */
public class SleepInterruptedException extends IllegalStateException {
    private static final long serialVersionUID = 1L;

    public SleepInterruptedException(InterruptedException cause) {
        super("sleep interrupted", cause);
    }
    
}
