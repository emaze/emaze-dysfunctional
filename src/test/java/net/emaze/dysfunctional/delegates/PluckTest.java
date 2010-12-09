package net.emaze.dysfunctional.delegates;

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
        final Pluck<String, DumbBean> p = new Pluck<String, DumbBean>(DumbBean.class, "something");
        String got = p.perform(db);
        Assert.assertEquals(db.getSomething(), got);
    }

    @Test
    public void canPluckIntFromBean() {
        DumbBean db = new DumbBean();
        final Pluck<Integer, DumbBean> p = new Pluck<Integer, DumbBean>(DumbBean.class, "one");
        int got = p.perform(db);
        Assert.assertEquals(db.getOne(), got);
    }

    @Test(expected = IllegalStateException.class)
    public void pluckingNonExistentPropertyYieldsException() {
        final Pluck<Integer, DumbBean> p = new Pluck<Integer, DumbBean>(DumbBean.class, "nonExistentProperty");
        p.perform(new DumbBean());
    }
}
