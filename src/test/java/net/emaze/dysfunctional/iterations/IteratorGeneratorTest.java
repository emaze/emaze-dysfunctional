package net.emaze.dysfunctional.iterations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class IteratorGeneratorTest {

    @Test
    public void generatingItemFromSeed() {
        final Box<O> seed = Box.<O>empty();
        final Delegate<Maybe<O>, O> generator = Spies.spy1st(new Sequence<O>(Arrays.asList(Maybe.<O>nothing())), seed);
        final IteratorGenerator<O> iterator = new IteratorGenerator<O>(O.ONE, generator);
        iterator.hasNext();
        Assert.assertEquals(O.ONE, seed.getContent());
    }

    @Test
    public void generatingNextItemUsingPreviousItem() {
        final Box<O> previous = Box.<O>empty();
        final Delegate<Maybe<O>, O> generator = Spies.spy1st(new Sequence<O>(Arrays.asList(Maybe.just(O.ANOTHER), Maybe.<O>nothing())), previous);
        final IteratorGenerator<O> iterator = new IteratorGenerator<O>(O.ONE, generator);
        iterator.next();
        iterator.hasNext();
        Assert.assertEquals(O.ANOTHER, previous.getContent());
    }

    @Test
    public void generatingNothingEndsIterator() {
        final Delegate<Maybe<O>, O> generator = new Sequence<O>(Arrays.asList(Maybe.<O>nothing()));
        final IteratorGenerator<O> iterator = new IteratorGenerator<O>(null, generator);
        Assert.assertEquals(false, iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void gettingUnavailableNextItemYieldsException() {
        final Delegate<Maybe<O>, O> generator = new Sequence<O>(Arrays.asList(Maybe.<O>nothing()));
        final IteratorGenerator<O> iterator = new IteratorGenerator<O>(null, generator);
        iterator.next();
    }

    @Test
    public void gettingNextItemWithoutHasNextInvocation() {
        final Delegate<Maybe<O>, O> generator = new Sequence<O>(Arrays.asList(
                Maybe.just(O.ONE),
                Maybe.just(O.ANOTHER),
                Maybe.<O>nothing()));
        final IteratorGenerator<O> iterator = new IteratorGenerator<O>(null, generator);
        Assert.assertEquals(O.ONE, iterator.next());
    }

    @Test
    public void gettingNextItemAfterHasNextInvocation() {
        final Delegate<Maybe<O>, O> generator = new Sequence<O>(Arrays.asList(
                Maybe.just(O.ONE),
                Maybe.just(O.ANOTHER),
                Maybe.<O>nothing()));
        final IteratorGenerator<O> iterator = new IteratorGenerator<O>(null, generator);
        iterator.hasNext();
        Assert.assertEquals(O.ONE, iterator.next());
    }

    @Test
    public void gettingNextItemAfterNextInvocation() {
        final Delegate<Maybe<O>, O> generator = new Sequence<O>(Arrays.asList(
                Maybe.just(O.ONE),
                Maybe.just(O.ANOTHER),
                Maybe.<O>nothing()));
        final IteratorGenerator<O> iterator = new IteratorGenerator<O>(null, generator);
        iterator.next();
        Assert.assertEquals(O.ANOTHER, iterator.next());
    }

    @Test
    public void endsAfterGettingLastItem() {
        final Delegate<Maybe<O>, O> generator = new Sequence<O>(Arrays.asList(
                Maybe.just(O.ONE),
                Maybe.just(O.ANOTHER),
                Maybe.<O>nothing()));
        final IteratorGenerator<O> iterator = new IteratorGenerator<O>(null, generator);
        iterator.next();
        iterator.next();
        Assert.assertEquals(false, iterator.hasNext());
    }

    @Test
    public void checkingNextItemAfterCompletionHasNoEffect() {
        final AtomicLong invocations = new AtomicLong();
        final Delegate<Maybe<O>, O> generator = Spies.monitor(new Sequence<O>(Arrays.asList(Maybe.<O>nothing())), invocations);
        final IteratorGenerator<O> iterator = new IteratorGenerator<O>(null, generator);
        iterator.hasNext();
        iterator.hasNext();
        Assert.assertEquals(1, invocations.get());
    }

    private class Sequence<T> implements Delegate<Maybe<T>, T> {

        private final Iterator<Maybe<T>> items;

        public Sequence(Iterable<Maybe<T>> items) {
            this.items = items.iterator();
        }

        @Override
        public Maybe<T> perform(T t) {
            return items.next();
        }
    }
}
