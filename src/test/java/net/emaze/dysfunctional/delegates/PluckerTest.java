package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.iterations.Iterations;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PluckerTest {

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
        final Plucker<String, DumbBean> p = new Plucker<String, DumbBean>("something");
        String got = p.perform(db);
        Assert.assertEquals(db.getSomething(), got);
    }
    
    @Test
    public void canPluckIntFromBean() {
        DumbBean db = new DumbBean();
        final Plucker<Integer, DumbBean> p = new Plucker<Integer, DumbBean>("one");
        int got = p.perform(db);
        Assert.assertEquals(db.getOne(), got);
    }
}
