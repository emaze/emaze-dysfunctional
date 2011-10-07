package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.Dispatching;

public class EndoDelegatesComposer<T> implements Delegate<Delegate<T, T>, Iterator<Delegate<T, T>>> {

    @Override
    public Delegate<T, T> perform(Iterator<Delegate<T, T>> endoDelegates) {
        Delegate<T, T> current = new Identity<T>();
        while (endoDelegates.hasNext()) {
            current = Dispatching.compose(endoDelegates.next(), current);
        }
        return current;
    }
}
