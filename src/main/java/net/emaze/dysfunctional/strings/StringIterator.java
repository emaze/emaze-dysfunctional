package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class StringIterator implements Iterator<Character> {
    private final String value;
    private int currentPosition;


    public StringIterator(String value) {
        dbc.precondition(value != null, "cannot create StringIterator with a null String");
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
        throw new UnsupportedOperationException("Cannot remove from a StringIterator.");
    }

}
