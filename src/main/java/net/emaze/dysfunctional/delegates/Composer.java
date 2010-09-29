package net.emaze.dysfunctional.delegates;

/**
 * given f, g yields f ° g (f of g, f following g)
 * @param <R>
 * @param <T>
 * @param <U> 
 * @author rferranti
 */
public class Composer<R,T,U> implements Delegate<R,T>{

    private final Delegate<U,T> g;
    private final Delegate<R,U> f;

    public Composer(Delegate<R, U> f, Delegate<U, T> g) {
        this.f = f;
        this.g = g;
    }

    @Override
    public R perform(T t) {
        return f.perform(g.perform(t));
    }
   
}
