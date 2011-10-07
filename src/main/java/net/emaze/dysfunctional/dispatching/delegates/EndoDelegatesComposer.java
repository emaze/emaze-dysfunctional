package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

public class EndoDelegatesComposer<T> implements Delegate<Delegate<T, T>, Iterator<Delegate<T, T>>> {

    @Override
    public Delegate<T, T> perform(Iterator<Delegate<T, T>> endoDelegates) {
        dbc.precondition(endoDelegates != null, "cannot compose a null iterator of endoDelegates");
        Delegate<T, T> current = new Identity<T>();
        while (endoDelegates.hasNext()) {
            current = new Composer<T, T, T>(endoDelegates.next(), current);
        }
        return current;
    }
}
