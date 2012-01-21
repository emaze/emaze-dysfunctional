package net.emaze.dysfunctional.contracts;

import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DbcTest {

    @Test(expected = IllegalArgumentException.class)
    public void failingPreconditionYieldsIllegalArgumentException() {
        dbc.precondition(false, "i'm a precondition failed message");
    }

    @Test(expected = IllegalStateException.class)
    public void failingStatePreconditionYieldsIllegalStateException() {
        dbc.state(false, "i'm a precondition failed message");
    }

    @Test
    public void dbcFacadeIsNotFinal() {
        new dbc() {
        };
    }
}
