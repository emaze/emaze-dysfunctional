package net.emaze.dysfunctional;

import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * widen, widener, narrow, narrower, vary, variator.
 *
 * @author rferranti
 */
public abstract class Casts {

    /**
     * Performs a downcast on a value.
     *
     * @param <R> the resulting type
     * @param <T> the source type
     * @param value the value to be cast
     * @return the resulting value
     */
    public static <R, T> R widen(T value) {
        return new Vary<R, T>().perform(value);
    }

    /**
     * Creates a delegate performing a widening cast.
     *
     * @param <T> the resulting type
     * @param <R> the source type
     * @return the delegate
     */
    public static <T, R extends T> Delegate<R, T> widener() {
        return new Vary<R, T>();
    }

    /**
     * Performs an upcast on a value.
     *
     * @param <R> the resulting type
     * @param <T> the source type
     * @param value the value to be cast
     * @return the resulting value
     */
    public static <R, T extends R> R narrow(T value) {
        return new Vary<R, T>().perform(value);
    }

    /**
     * Creates a delegate performing a narrowing cast.
     *
     * @param <R> the resulting type
     * @param <T> the source type
     * @return the delegate
     */
    public static <R, T extends R> Delegate<R, T> narrower() {
        return new Vary<R, T>();
    }

    /**
     * Performs a reinterpret cast on a value.
     *
     * @param <R> the resulting type
     * @param <T> the source type
     * @param value the value to be cast
     * @return the resulting value
     */
    public static <R, T> R vary(T value) {
        return new Vary<R, T>().perform(value);
    }

    /**
     * Creates a delegate performing a reinterpret cast on a value.
     *
     * @param <R> the resulting type
     * @param <T> the source type
     * @return the delegate
     */
    public static <R, T> Delegate<R, T> variator() {
        return new Vary<R, T>();
    }
}
