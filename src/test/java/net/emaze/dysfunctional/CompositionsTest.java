package net.emaze.dysfunctional;

import java.util.Iterator;
import java.util.function.*;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class CompositionsTest {

    @Test
    public void canComposeFunctionAndSupplier() {
        final Supplier<Integer> c = Compositions.compose(Function.identity(), () -> 1);
        Assert.assertEquals(1, (int) c.get());
    }

    @Test
    public void canComposeTwoFunctions() {
        final Function<Integer, Integer> c = Compositions.compose(Function.identity(), Function.identity());
        Assert.assertEquals(1, (int) c.apply(1));
    }

    @Test
    public void canComposeThreeFunctions() {
        final Function<O, O> composed = Compositions.compose(Function.identity(), Function.identity(), Function.identity());
        Assert.assertNotNull(composed);
    }

    @Test
    public void canComposeFunctionWithBinaryFunction() {
        final BiFunction<O, O, O> composer = Compositions.compose((O o) -> new O(o.toString() + " is composed"), (fst, snd) -> fst);
        final O got = composer.apply(O.ONE, O.IGNORED);
        Assert.assertEquals("ONE is composed", got.toString());
    }
    
       @Test
    public void canComposeDelegateWithTernaryDelegate() {
        final TriFunction<O, O, O, O> composer = Compositions.compose((O o) -> new O(o.toString() + " is composed"), (fst, snd, trd) -> fst);
        final O got = composer.apply(O.ONE, O.IGNORED, O.IGNORED);
        Assert.assertEquals("ONE is composed", got.toString());
    }
    @Test
    public void canComposePredicatesAndFunctions() {
        final Predicate<O> composed = Compositions.compose(new Always<O>(), Function.identity());
        final boolean got = composed.test(O.IGNORED);
        Assert.assertEquals(true, got);
    }

    @Test
    public void canComposePredicatesAndBinaryFunctions() {
        final BiPredicate<O, O> composed = Compositions.compose(new Always<O>(), new FirstParam<O, O>());
        final boolean got = composed.test(O.IGNORED, O.IGNORED);
        Assert.assertEquals(true, got);
    }

    @Test
    public void canComposePredicateAndTernaryFunctions() {
        final TriPredicate<O, O, O> composed = Compositions.compose(new Always<O>(), new FirstParamOfThree<O, O, O>());
        final boolean got = composed.test(O.IGNORED, O.IGNORED, O.IGNORED);
        Assert.assertEquals(true, got);
    }

    @Test
    public void canComposeEndoFunctions() {
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
