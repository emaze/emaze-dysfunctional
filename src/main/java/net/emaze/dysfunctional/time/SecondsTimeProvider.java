package net.emaze.dysfunctional.time;

import net.emaze.dysfunctional.dispatching.delegates.Provider;

public class SecondsTimeProvider implements Provider<Integer> {

    @Override
    public Integer provide() {
        return (int) System.currentTimeMillis() / 1000;
    }
}
