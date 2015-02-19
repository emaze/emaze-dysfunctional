package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.logic.Always;
import java.util.function.Predicate;
import java.util.function.BooleanSupplier;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class PredicateBinderTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPredicateYieldsException() {
        new PredicateBinder<O>(null, O.IGNORED);
    }

    @Test
    public void callingTheBinderPassesBoundParameter() {
        final Box<O> param = Box.empty();
        final Predicate<O> spy = Spies.spy1st(new Always<O>(), param);
        final BooleanSupplier proposition = new PredicateBinder<O>(spy, O.ONE);
        proposition.getAsBoolean();
        Assert.assertEquals(O.ONE, param.getContent());
    }

    @Test
    public void callingTheBinderYieldsPredicateResult() {
        final Box<Boolean> result = Box.empty();
        final Predicate<O> spy = Spies.spyRes(new Always<O>(), result);
        final BooleanSupplier proposition = new PredicateBinder<O>(spy, O.ONE);
        proposition.getAsBoolean();
        Assert.assertEquals(Boolean.TRUE, result.getContent());
    }
}