package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.Dispatching;
import net.emaze.dysfunctional.reductions.Reductions;

public class EndoDelegatesComposer<T> implements Delegate<Delegate<T, T>, Iterator<Delegate<T, T>>> {

    @Override
    public Delegate<T, T> perform(Iterator<Delegate<T, T>> endofunctors) {
        return Reductions.reduce(endofunctors, new BinaryDelegate<Delegate<T, T>, Delegate<T, T>, Delegate<T, T>>() {

            @Override
            public Delegate<T, T> perform(Delegate<T, T> t1, Delegate<T, T> t2) {
                return Dispatching.compose(t1, t2);
            }
        }, new Identity<T>());
    }
}
