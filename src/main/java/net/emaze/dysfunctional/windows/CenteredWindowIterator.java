package net.emaze.dysfunctional.windows;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Supplier;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import java.util.Optional;

/**
 * [1,2,3,4,5], 3 -> (-,1,2), (1,2,3), (2,3,4), (3,4,5), (4,5,-)
 * @param <T>
 * @author rferranti
 */
public class CenteredWindowIterator<W extends Collection<Optional<T>>, T> extends ReadOnlyIterator<W> {

    private final Iterator<T> iter;
    private final int windowSize;
    private final Supplier<W> supplier;
    private final LinkedList<Optional<T>> window = new LinkedList<Optional<T>>();
    private boolean freshIterator = true;

    public CenteredWindowIterator(Iterator<T> iter, int windowSize, Supplier<W> supplier) {
        dbc.precondition(iter != null, "cannot create a CenteredWindowIterator with a null iterator");
        dbc.precondition(windowSize > 2, "cannot create a CenteredWindowIterator with a non positive or 1 window size");
        dbc.precondition(windowSize % 2 == 1, "cannot create a CenteredWindowIterator with an even windowSize");
        dbc.precondition(supplier != null, "cannot create a CenteredWindowIterator with an null supplier");
        this.iter = iter;
        this.windowSize = windowSize;
        this.supplier = supplier;
        for (int i = 0; i != windowSize / 2; ++i) {
            window.add(Optional.<T>empty());
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
        final W collection = supplier.get();
        collection.addAll(window);
        return collection;
    }

    private boolean isConsumed() {
        if (freshIterator) {
            return !center().isPresent();
        }
        return !nextOfCenter().isPresent();
    }

    private Optional<T> nextOfCenter() {
        return window.get(windowSize / 2 + 1);
    }

    private Optional<T> center() {
        return window.get(windowSize / 2);
    }

    private void fillWindow() {
        while (window.size() != windowSize) {
            final Optional<T> maybe = iter.hasNext()
                    ? Optional.of(iter.next())
                    : Optional.<T>empty();
            window.add(maybe);
        }
    }
}
