package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;

public class WarpingKnobsTest {

    @Test
    public void warpingKnobsTimeUnitIsMilliseconds() {
        Assert.assertEquals(TimeUnit.MILLISECONDS, new WarpingKnobs().state().second());
    }

    @Test
    public void defaultStateIsEpoch() {
        Assert.assertEquals(Long.valueOf(0), new WarpingKnobs().state().first());
    }

    @Test
    public void canMoveInTheFuture() {
        final WarpingKnobs warpingKnobs = new WarpingKnobs();
        warpingKnobs.add(1, TimeUnit.MILLISECONDS);
        Assert.assertEquals(Long.valueOf(1), warpingKnobs.state().first());
    }

    @Test
    public void canMoveInThePast() {
        final WarpingKnobs warpingKnobs = new WarpingKnobs();
        warpingKnobs.add(-1, TimeUnit.MILLISECONDS);
        Assert.assertEquals(Long.valueOf(-1), warpingKnobs.state().first());
    }

    @Test
    public void canWarpInTime() {
        final WarpingKnobs warpingKnobs = new WarpingKnobs();
        warpingKnobs.add(2000, TimeUnit.MILLISECONDS);
        Assert.assertEquals(Long.valueOf(2000), warpingKnobs.state().first());
    }

    @Test(expected = IllegalArgumentException.class)
    public void warpingWithNullTimeUnitYieldsException() {
        final WarpingKnobs warpingKnobs = new WarpingKnobs();
        warpingKnobs.warp(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingWithNullTimeUnitYieldsException() {
        final WarpingKnobs warpingKnobs = new WarpingKnobs();
        warpingKnobs.add(1, null);
    }
}