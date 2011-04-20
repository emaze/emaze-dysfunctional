package net.emaze.dysfunctional.time;

import net.emaze.dysfunctional.dispatching.delegates.Provider;

public class MillisecondsTimeProvider implements Provider<Long> {

    @Override
    public Long provide() {
        return System.currentTimeMillis();
    }
}
