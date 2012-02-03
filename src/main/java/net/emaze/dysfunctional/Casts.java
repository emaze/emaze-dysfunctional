package net.emaze.dysfunctional;

import net.emaze.dysfunctional.casts.Vary;
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
        return new Vary<R, T>().perform(value);
    }

    /**
     *
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R extends T> Delegate<R, T> widener() {
        return new Vary<R, T>();
    }

    /**
     *
     * @param <R>
     * @param <T>
     * @param value
     * @return
     */
    public static <R, T extends R> R narrow(T value) {
        return new Vary<R, T>().perform(value);
    }

    /**
     *
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, T extends R> Delegate<R, T> narrower() {
        return new Vary<R, T>();
    }

    /**
     *
     * @param <T>
     * @param <R>
     * @return
     */
    public static <R, T> R vary(T value) {
        return new Vary<R, T>().perform(value);
    }

    /**
     *
     * @param <T>
     * @param <R>
     * @return
     */
    public static <R, T> Delegate<R, T> variator() {
        return new Vary<R, T>();
    }
}
