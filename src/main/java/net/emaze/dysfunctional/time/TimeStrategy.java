package net.emaze.dysfunctional.time;

/**
 *
 * @author rferranti
 */
public interface TimeStrategy {
    long currentTimeMillis();
    void sleep(long millis);
}
