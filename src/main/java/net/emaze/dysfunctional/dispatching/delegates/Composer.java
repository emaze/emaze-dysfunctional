package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * Given f, g yields f ° g (f of g, f following g).
 * @param <R> the return Type
 * @param <T1> the former element Type
 * @param <T2> the latter element Type
 * @author rferranti
 */
public class Composer<R, T2, T1> implements Delegate<R, T1> {

    private final Delegate<T2, T1> g;
    private final Delegate<R, T2> f;

    public Composer(Delegate<R, T2> f, Delegate<T2, T1> g) {
        dbc.precondition(f != null, "cannot compose a null delegate");
        dbc.precondition(g != null, "cannot compose a null delegate");
        this.f = f;
        this.g = g;
    }

    @Override
    public R perform(T1 t) {
        return f.perform(g.perform(t));
    }
}
