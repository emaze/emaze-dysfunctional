package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class CompositionsTest {

    @Test
    public void canComposeTwoDelegates() {
        Delegate<O, O> composed = Compositions.compose(new Identity<O>(), new Identity<O>());
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposeThreeDelegates() {
        final Identity<O> i = new Identity<O>();
        Delegate<O, O> composed = Compositions.compose(i, i, i);
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposePredicatesAndDelegates() {
        Predicate<O> composed = Compositions.compose(new Always<O>(), new Identity<O>());
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposePredicateAndTwoDelegates() {
        final Identity<O> i = new Identity<O>();
        Predicate<O> composed = Compositions.compose(new Always<O>(), i, i);
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposePredicatesAndBinaryDelegates() {
        BinaryPredicate<O, O> composed = Compositions.compose(new Always<O>(), new FirstParam<O, O>());
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposePredicateAndTernaryDelegates() {
        TernaryPredicate<O, O, O> composed = Compositions.compose(new Always<O>(), new FirstParamOfThree<O, O, O>());
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposeEndoDelegates() {
        final Iterator<Delegate<O, O>> delegates = Iterations.<Delegate<O, O>>iterator(new Identity<O>(), new Identity<O>());
        final Delegate<O, O> composed = Compositions.compose(delegates);
        Assert.assertNotNull(composed);
    }

    @Test
    public void facadeIsNotFinal() {
        new Compositions() {
        };
    }
}
