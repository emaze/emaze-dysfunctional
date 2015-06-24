package net.emaze.dysfunctional;

import net.emaze.dysfunctional.casts.Vary;
import java.util.function.Function;

/**
 * widen, widener, narrow, narrower, vary, variator.
 *
 * @author rferranti
 */
public abstract class Casts {

    /**
     * Performs a downcast on a value.
     *
     * @param <T> the source type
     * @param <R> the resulting type
     * @param value the value to be cast
     * @return the resulting value
     */
    public static <T, R> R widen(T value) {
        return new Vary<T, R>().apply(value);
    }

    /**
     * Creates a function performing a widening cast.
     *
     * @param <T> the resulting type
     * @param <R> the source type
     * @return the function
     */
    public static <T, R extends T> Function<T, R> widener() {
        return new Vary<>();
    }

    /**
     * Performs an upcast on a value.
     *
     * @param <T> the source type
     * @param <R> the resulting type
     * @param value the value to be cast
     * @return the resulting value
     */
    public static <T extends R, R> R narrow(T value) {
        return new Vary<T, R>().apply(value);
    }

    /**
     * Creates a function performing a narrowing cast.
     *
     * @param <R> the resulting type
     * @param <T> the source type
     * @return the function
     */
    public static <T extends R, R> Function<T, R> narrower() {
        return new Vary<>();
    }

    /**
     * Performs a reinterpret cast on a value.
     *
     * @param <R> the resulting type
     * @param <T> the source type
     * @param value the value to be cast
     * @return the resulting value
     */
    public static <T, R> R vary(T value) {
        return new Vary<T, R>().apply(value);
    }

    /**
     * Creates a function performing a reinterpret cast on a value.
     *
     * @param <T> the source type
     * @param <R> the resulting type
     * @return the function
     */
    public static <R, T> Function<T, R> variator() {
        return new Vary<>();
    }
}
