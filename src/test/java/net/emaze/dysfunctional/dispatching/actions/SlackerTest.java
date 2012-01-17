package net.emaze.dysfunctional.dispatching.actions;

import org.junit.Test;

public class SlackerTest {

    @Test
    public void evenASlackerCanRun() {
        new Slacker().run();
    }
}