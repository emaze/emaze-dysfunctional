package net.emaze.dysfunctional.dispatching.delegates;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PluckTest {

    public static class DumbBean {

        public String getSomething() {
            return "something";
        }

        public int getOne() {
            return 1;
        }
    }

    @Test
    public void canPluckStringFromBean() {
        DumbBean db = new DumbBean();
        final Pluck<DumbBean, String> p = new Pluck<>(DumbBean.class, "something");
        String got = p.apply(db);
        Assert.assertEquals(db.getSomething(), got);
    }

    @Test
    public void canPluckIntFromBean() {
        DumbBean db = new DumbBean();
        final Pluck<DumbBean, Integer> p = new Pluck<>(DumbBean.class, "one");
        int got = p.apply(db);
        Assert.assertEquals(db.getOne(), got);
    }

    @Test(expected = IllegalStateException.class)
    public void pluckingNonExistentPropertyYieldsException() {
        final Pluck<DumbBean, Integer> p = new Pluck<>(DumbBean.class, "nonExistentProperty");
        p.apply(new DumbBean());
    }
}
