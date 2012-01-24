package net.emaze.dysfunctional;

import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.casts.Widen;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public abstract class Casts {

    /**
     *
     * @param <R>
     * @param <T>
     * @param value
     * @return
     */
    public static <R, T> R widen(T value) {
        return new Widen<R, T>().perform(value);
    }

    /**
     *
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R extends T> Delegate<R, T> widen() {
        return new Widen<R, T>();
    }

    /**
     *
     * @param <R>
     * @param <T>
     * @param value
     * @return
     */
    public static <R, T extends R> R narrow(T value) {
        return new Narrow<R, T>().perform(value);
    }

    /**
     *
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, T extends R> Delegate<R, T> narrow() {
        return new Narrow<R, T>();
    }
}
