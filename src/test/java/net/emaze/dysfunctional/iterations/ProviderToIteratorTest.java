package net.emaze.dysfunctional.iterations;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class ProviderToIteratorTest {

    @Test
    public void providingNothingEndsIterator() {
        final Provider<Maybe<O>> provider = new SequentialProvider<O>();
        final ProviderToIterator<O> iterator = new ProviderToIterator<O>(provider);
        Assert.assertEquals(false, iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void gettingUnavailableNextItemYieldsException() {
        final Provider<Maybe<O>> provider = new SequentialProvider<O>();
        final ProviderToIterator<O> iterator = new ProviderToIterator<O>(provider);
        iterator.next();
    }

    @Test
    public void gettingNextItemWithoutHasNextInvocation() {
        final Provider<Maybe<O>> provider = new SequentialProvider<O>(O.ONE, O.ANOTHER);
        final ProviderToIterator<O> iterator = new ProviderToIterator<O>(provider);
        Assert.assertEquals(O.ONE, iterator.next());
    }

    @Test
    public void gettingNextItemAfterHasNextInvocation() {
        final Provider<Maybe<O>> provider = new SequentialProvider<O>(O.ONE, O.ANOTHER);
        final ProviderToIterator<O> iterator = new ProviderToIterator<O>(provider);
        iterator.hasNext();
        Assert.assertEquals(O.ONE, iterator.next());
    }

    @Test
    public void gettingNextItemAfterNextInvocation() {
        final Provider<Maybe<O>> provider = new SequentialProvider<O>(O.ONE, O.ANOTHER);
        final ProviderToIterator<O> iterator = new ProviderToIterator<O>(provider);
        iterator.next();
        Assert.assertEquals(O.ANOTHER, iterator.next());
    }

    @Test
    public void endsAfterGettingLastItem() {
        final Provider<Maybe<O>> provider = new SequentialProvider<O>(O.ONE, O.ANOTHER);
        final ProviderToIterator<O> iterator = new ProviderToIterator<O>(provider);
        iterator.next();
        iterator.next();
        Assert.assertEquals(false, iterator.hasNext());
    }

    @Test
    public void checkingNextItemAfterCompletionHasNoEffect() {
        final AtomicLong invocations = new AtomicLong();
        final Provider<Maybe<O>> provider = Spies.monitor(new SequentialProvider<O>(), invocations);
        final ProviderToIterator<O> iterator = new ProviderToIterator<O>(provider);
        iterator.hasNext();
        iterator.hasNext();
        Assert.assertEquals(1, invocations.get());
    }

    private class SequentialProvider<T> implements Provider<Maybe<T>> {

        private final T[] items;
        private int index = 0;

        public SequentialProvider(T... items) {
            this.items = items;
        }

        @Override
        public Maybe<T> provide() {
            if (index == items.length) {
                return Maybe.nothing();
            }
            return Maybe.just(items[index++]);
        }
    }
}
