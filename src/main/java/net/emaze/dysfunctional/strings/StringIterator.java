package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author rferranti
 */
public class StringIterator implements Iterator<Character> {
    private final String value;
    private int currentPosition;


    public StringIterator(String value) {
        this.value = value;
        this.currentPosition = 0;
    }

    @Override
    public boolean hasNext() {
        return !limitReached();
    }

    private boolean limitReached() {
        return currentPosition == value.length();
    }

    @Override
    public Character next() {
        if(limitReached()){
            throw new NoSuchElementException();
        }
        return value.charAt(currentPosition++);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
