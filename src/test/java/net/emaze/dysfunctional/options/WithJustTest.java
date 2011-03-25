package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.Identity;
import org.junit.Assert;
import org.junit.Test;

public class WithJustTest {


    @Test(expected=IllegalArgumentException.class)
    public void creatingWithJustWithNullDelegateYieldsException(){
        new WithJust<Object, Object>(null);
    }

    @Test
    public void canPerformWithJust(){
        final Object expected = new Object();
        final WithJust<Object, Object> w = new WithJust<Object, Object>(new Identity<Object>());
        Assert.assertEquals(Maybe.just(expected), w.perform(Maybe.just(expected)));
    }
}