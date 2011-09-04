package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * 
 * @param <T> 
 * @author rferranti
 */
public class AtMostMemoryIterator<T> implements Iterator<T> {

    private final Iterator<T> iterator;
    private final int memorySize;
    private Queue<T> memory;
    private boolean hasMemory;

    public AtMostMemoryIterator(Iterator<T> iterator, int memorySize) {
        dbc.precondition(iterator != null, "creating a MemoryIterator with a null iterator");
        dbc.precondition(memorySize > 0, "creating a MemoryIterator with a non positive memorySize");
        this.iterator = iterator;
        this.memorySize = memorySize;
    }

    @Override
    public boolean hasNext() {
        if (!hasMemory) {
            memory = fetch(iterator, memorySize);
            hasMemory = true;
        }
        return !memory.isEmpty();
    }

    @Override
    public T next() {
        if (!hasMemory) {
            memory = fetch(iterator, memorySize);
            hasMemory = true;
        }
        return memory.remove();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("not implemented yet");
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
        return mem;
    }
}
