package net.emaze.dysfunctional.windows;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * [1,2,3,4,5], 3 -> (1,2,3), (2,3,4), (3,4,5)
 * @param <T>
 * @author rferranti
 */
public class PreciseWindowIterator<T> implements Iterator<Queue<T>> {

    private final Iterator<T> iter;
    private final int windowSize;
    private final Queue<T> window = new LinkedList<T>();

    public PreciseWindowIterator(Iterator<T> iter, int windowSize) {
        dbc.precondition(iter != null, "cannot create a PreciseWindowIterator with a null iterator");
        dbc.precondition(windowSize > 0, "cannot create a PreciseWindowIterator with a non positive window size");
        this.iter = iter;
        this.windowSize = windowSize;
    }

    @Override
    public boolean hasNext() {
        if (window.size() == windowSize) {
            return iter.hasNext();
        }
        tryFillWindow();
        return window.size() == windowSize;
    }

    @Override
    public Queue<T> next() {
        if (window.size() == windowSize) {
            window.add(iter.next());
            window.remove();
        }
        tryFillWindow();
        return window;
    }

    @Override
    public void remove() {
        throw new IllegalStateException("Cannot remove from a PreciseWindowIterator");
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
