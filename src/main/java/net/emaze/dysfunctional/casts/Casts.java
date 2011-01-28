package net.emaze.dysfunctional.casts;

/**
 *
 * @author rferranti
 */
public abstract class Casts {

    public static <W, T> W widen(T t) {
        return (W) t;
    }
}
