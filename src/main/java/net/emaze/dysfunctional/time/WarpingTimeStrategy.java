package net.emaze.dysfunctional.time;

/**
 *
 * @author rferranti
 */
public class WarpingTimeStrategy implements TimeStrategy {

    private long now;

    public WarpingTimeStrategy(long now) {
        this.now = now;
    }

    @Override
    public long currentTimeMillis() {
        return now;
    }

    @Override
    public void sleep(long millis) {
        now += millis;
    }

    public void warp(long sinceEpoch) {
        now = sinceEpoch;
    }
}
