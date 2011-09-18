package net.emaze.dysfunctional.strings;

import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;

/**
 *
 * @author rferranti
 */
public class StringIterator extends ReadOnlyIterator<Character> {
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
            throw new NoSuchElementException("iterator is consumed");
        }
        return value.charAt(currentPosition++);
    }
}
