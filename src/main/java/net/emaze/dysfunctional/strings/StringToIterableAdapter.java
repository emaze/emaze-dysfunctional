package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class StringToIterableAdapter implements Iterable<Character> {

    private final String value;

    public StringToIterableAdapter(String value) {
        dbc.precondition(value != null, "trying to create a StringToIterableAdapter from a null array");
        this.value = value;
    }

    @Override
    public Iterator<Character> iterator() {
        return new StringIterator(value);
    }
}
