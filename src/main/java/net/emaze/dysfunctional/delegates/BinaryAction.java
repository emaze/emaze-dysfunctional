package net.emaze.dysfunctional.delegates;

/**
 *
 * @author rferranti
 */
public interface BinaryAction<T1,T2> {
    public void perform(T1 t1, T2 t2);
}
