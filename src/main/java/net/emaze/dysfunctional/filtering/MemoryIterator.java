package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Memoized;

/**
 * 
 * @param <T> 
 * @author rferranti
 */
public class MemoryIterator<T> implements Iterator<T> {

    private final Iterator<T> iterator;
    private final Memoized<Queue<T>> memory = new Memoized<Queue<T>>();
    private final int memorySize;

    public MemoryIterator(Iterator<T> iterator, int memorySize) {
        dbc.precondition(iterator != null, "creating a MemoryIterator with a null iterator");
        dbc.precondition(memorySize > 0, "creating a MemoryIterator with a non positive memorySize");
        this.iterator = iterator;
        this.memorySize = memorySize;
    }

    @Override
    public boolean hasNext() {
        if (!memory.isMemoized()) {
            memory.memoize(fetch(iterator, memorySize));
        }
        return !memory.value().isEmpty();
    }

    private static <T> Queue<T> fetch(Iterator<T> iterator, int size) {
        final Queue<T> mem = new LinkedList<T>();
        while (iterator.hasNext()) {
            final T element = iterator.next();
            mem.add(element);
            if (mem.size() > size) {
                mem.remove();
            }
        }
        dbc.stateprecondition(mem.size() == size, "iterator used by MemoryIterator is too short to memorize last %s elements", size);
        return mem;
    }

    @Override
    public T next() {
        if (!memory.isMemoized()) {
            memory.memoize(fetch(iterator, memorySize));
        }
        return memory.value().remove();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
