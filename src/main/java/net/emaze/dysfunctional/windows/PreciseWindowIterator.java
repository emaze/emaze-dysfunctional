package net.emaze.dysfunctional.windows;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Supplier;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;

/**
 * [1,2,3,4,5], 3 -> (1,2,3), (2,3,4), (3,4,5)
 * @param <T>
 * @author rferranti
 */
public class PreciseWindowIterator<W extends Collection<T>, T> extends ReadOnlyIterator<W> {

    private final Iterator<T> iter;
    private final int windowSize;
    private final Supplier<W> supplier;
    private final Queue<T> window = new LinkedList<T>();

    public PreciseWindowIterator(Iterator<T> iter, int windowSize, Supplier<W> supplier) {
        dbc.precondition(iter != null, "cannot create a PreciseWindowIterator with a null iterator");
        dbc.precondition(windowSize > 0, "cannot create a PreciseWindowIterator with a non positive window size");
        dbc.precondition(supplier != null, "cannot create a CenteredWindowIterator with an null supplier");
        this.iter = iter;
        this.windowSize = windowSize;
        this.supplier = supplier;
    }

    @Override
    public boolean hasNext() {
        if (window.size() == windowSize) {
            return true;
        }
        tryFillWindow();
        return window.size() == windowSize;
    }

    @Override
    public W next() {
        if(!hasNext()) { 
            throw new NoSuchElementException("iterator is consumed");
        }
        final W collection = supplier.get();
        collection.addAll(window);
        window.remove();
        return collection;
    }

    private void tryFillWindow() {
        while (window.size() != windowSize) {
            if (!iter.hasNext()) {
                return;
            }
            window.add(iter.next());
        }
    }
}
