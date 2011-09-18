package net.emaze.dysfunctional.windows;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.options.Maybe;

/**
 * [1,2,3,4,5], 3 -> (-,1,2), (1,2,3), (2,3,4), (3,4,5), (4,5,-)
 * @param <T>
 * @author rferranti
 */
public class CenteredWindowIterator<W extends Collection<Maybe<T>>, T> implements Iterator<W> {

    private final Iterator<T> iter;
    private final int windowSize;
    private final Provider<W> provider;    
    private final LinkedList<Maybe<T>> window = new LinkedList<Maybe<T>>();
    private boolean freshIterator = true;

    public CenteredWindowIterator(Iterator<T> iter, int windowSize, Provider<W> provider) {
        dbc.precondition(iter != null, "cannot create a CenteredWindowIterator with a null iterator");
        dbc.precondition(windowSize > 2, "cannot create a CenteredWindowIterator with a non positive or 1 window size");
        dbc.precondition(windowSize % 2 == 1, "cannot create a CenteredWindowIterator with an even windowSize");
        dbc.precondition(provider != null, "cannot create a CenteredWindowIterator with an null provider");
        this.iter = iter;
        this.windowSize = windowSize;
        this.provider = provider;        
        for (int i = 0; i != windowSize / 2; ++i) {
            window.add(Maybe.<T>nothing());
        }
    }

    @Override
    public boolean hasNext() {
        fillWindow();
        return !isConsumed();
    }

    @Override
    public W next() {
        fillWindow();
        if (isConsumed()) {
            throw new NoSuchElementException("iterator is consumed");
        }
        if (!freshIterator) {
            window.remove();
        }
        freshIterator = false;
        fillWindow();
        final W collection = provider.provide();
        collection.addAll(window);
        return collection;
    }

    private boolean isConsumed() {
        if (freshIterator) {
            return !center().hasValue();
        }
        return !nextOfCenter().hasValue();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove from a CenteredWindowIterator.");
    }

    private Maybe<T> nextOfCenter() {
        return window.get(windowSize / 2 + 1);
    }

    private Maybe<T> center() {
        return window.get(windowSize / 2);
    }

    private void fillWindow() {
        while (window.size() != windowSize) {
            final Maybe<T> maybe = iter.hasNext()
                    ? Maybe.just(iter.next())
                    : Maybe.<T>nothing();
            window.add(maybe);
        }
    }
}
