package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public interface TimeStrategy {

    Pair<Long, TimeUnit> currentTime();

    void sleep(long duration, TimeUnit unit);
}
