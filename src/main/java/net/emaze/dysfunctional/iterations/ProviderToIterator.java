package net.emaze.dysfunctional.iterations;

import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.options.Maybe;

/**
 * Adapts a provider of {@code Maybe<T>} to a iterator of {@code T}.
 * The iterator ends when the provider returns nothing.
 */
public class ProviderToIterator<T> extends ReadOnlyIterator<T> {

    private final Provider<Maybe<T>> provider;
    private final Box<T> next = Box.empty();
    private boolean active = true;

    public ProviderToIterator(Provider<Maybe<T>> provider) {
        dbc.precondition(provider != null, "provider cannot be null");
        this.provider = provider;
    }

    @Override
    public boolean hasNext() {
        return fetchNext();
    }

    @Override
    public T next() {
        if (!fetchNext()) {
            throw new NoSuchElementException("Provider has been already consumed");
        }
        return next.unload().value();
    }

    private boolean fetchNext() {
        if (active && next.isEmpty()) {
            final Maybe<T> got = provider.provide();
            active = got.hasValue();
            if (active) {
                next.setContent(got.value());
            }
        }
        return active;
    }
}
