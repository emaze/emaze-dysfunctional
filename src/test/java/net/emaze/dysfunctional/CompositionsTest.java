package net.emaze.dysfunctional;

import java.util.Iterator;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class CompositionsTest {

    @Test
    public void canComposeTwoDelegates() {
        final Function<O, O> composed = Compositions.compose(Function.identity(), Function.identity());
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposeThreeDelegates() {
        final Function<O, O> composed = Compositions.compose(Function.identity(), Function.identity(), Function.identity());
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposePredicatesAndDelegates() {
        final Predicate<O> composed = Compositions.compose(new Always<O>(), Function.identity());
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposePredicateAndTwoDelegates() {
        final Predicate<O> composed = Compositions.compose(new Always<O>(), Function.identity(), Function.identity());
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposePredicatesAndBinaryDelegates() {
        final BiPredicate<O, O> composed = Compositions.compose(new Always<O>(), new FirstParam<O, O>());
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposePredicateAndTernaryDelegates() {
        final TriPredicate<O, O, O> composed = Compositions.compose(new Always<O>(), new FirstParamOfThree<O, O, O>());
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposeEndoDelegates() {
        final Iterator<Function<O, O>> delegates = Iterations.<Function<O, O>>iterator(Function.identity(), Function.identity());
        final Function<O, O> composed = Compositions.compose(delegates);
        Assert.assertNotNull(composed);
    }

    @Test
    public void facadeIsNotFinal() {
        new Compositions() {
        };
    }
}
