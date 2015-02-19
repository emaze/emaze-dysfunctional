package net.emaze.dysfunctional;

import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.composing.PipelinedAction;
import net.emaze.dysfunctional.dispatching.composing.PipelinedBinaryAction;
import net.emaze.dysfunctional.dispatching.composing.PipelinedTernaryAction;

/**
 * pipeline.
 *
 * @author rferranti
 */
public abstract class Pipelines {

    /**
     * pipeline.
     */
    public abstract static class Unary {

        /**
         * Creates a pipeline from an iterable of actions.
         *
         * @param <T> the action parameter type
         * @param actions the iterable to be transformed
         * @return the pipelined action
         */
        public static <T> Consumer<T> pipeline(Iterable<Consumer<T>> actions) {
            return new PipelinedAction<T>(actions);
        }

        /**
         * Creates a pipeline from an iterator of actions.
         *
         * @param <T> the action parameter type
         * @param actions the iterator to be transformed
         * @return the pipelined action
         */
        public static <T> Consumer<T> pipeline(Iterator<Consumer<T>> actions) {
            return new PipelinedAction<T>(Consumers.all(actions));
        }
    }

    /**
     * pipeline.
     */
    public abstract static class Binary {

        /**
         * Creates a pipeline from an iterable of binary actions.
         *
         * @param <T1> the action first parameter type
         * @param <T2> the action second parameter type
         * @param actions the iterable to be transformed
         * @return the pipelined action
         */
        public static <T1, T2> BiConsumer<T1, T2> pipeline(Iterable<BiConsumer<T1, T2>> actions) {
            return new PipelinedBinaryAction<T1, T2>(actions);
        }

        /**
         * Creates a pipeline from an iterator of binary actions.
         *
         * @param <T1> the action first parameter type
         * @param <T2> the action second parameter type
         * @param actions the iterator to be transformed
         * @return the pipelined action
         */
        public static <T1, T2> BiConsumer<T1, T2> pipeline(Iterator<BiConsumer<T1, T2>> actions) {
            return new PipelinedBinaryAction<T1, T2>(Consumers.all(actions));
        }
    }

    /**
     * pipeline.
     */
    public abstract static class Ternary {

        /**
         * Creates a pipeline from an iterable of ternary actions.
         *
         * @param <T1> the action first parameter type
         * @param <T2> the action second parameter type
         * @param <T3> the action third parameter type
         * @param actions the iterable to be transformed
         * @return the pipelined action
         */
        public static <T1, T2, T3> TernaryAction<T1, T2, T3> pipeline(Iterable<TernaryAction<T1, T2, T3>> actions) {
            return new PipelinedTernaryAction<T1, T2, T3>(actions);
        }

        /**
         * Creates a pipeline from an iterator of ternary actions.
         *
         * @param <T1> the action first parameter type
         * @param <T2> the action second parameter type
         * @param <T3> the action third parameter type
         * @param actions the iterator to be transformed
         * @return the pipelined action
         */
        public static <T1, T2, T3> TernaryAction<T1, T2, T3> pipeline(Iterator<TernaryAction<T1, T2, T3>> actions) {
            return new PipelinedTernaryAction<T1, T2, T3>(Consumers.all(actions));
        }
    }

    /**
     * Creates a pipeline from an action.
     *
     * @param <T> the action parameter type
     * @param action the action to be transformed
     * @return the pipelined action
     */
    public static <T> Consumer<T> pipeline(Consumer<T> action) {
        return new PipelinedAction<T>(Iterations.iterable(action));
    }

    /**
     * Creates a pipeline from two actions.
     *
     * @param <T> the action parameter type
     * @param former the former action
     * @param latter the latter action
     * @return the pipelined action
     */
    public static <T> Consumer<T> pipeline(Consumer<T> former, Consumer<T> latter) {
        return new PipelinedAction<T>(Iterations.iterable(former, latter));
    }

    /**
     * Creates a pipeline from three actions.
     *
     * @param <T> the action parameter type
     * @param first the first action
     * @param second the second action
     * @param third the third action
     * @return the pipelined action
     */
    public static <T> Consumer<T> pipeline(Consumer<T> first, Consumer<T> second, Consumer<T> third) {
        return new PipelinedAction<T>(Iterations.iterable(first, second, third));
    }

    /**
     * Creates a pipeline from an array of actions.
     *
     * @param <T> the action parameter type
     * @param actions the array of actions to be transformed
     * @return the pipelined action
     */
    public static <T> Consumer<T> pipeline(Consumer<T>... actions) {
        return new PipelinedAction<T>(Iterations.iterable(actions));
    }

    /**
     * Creates a pipeline from a binary action.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param action the action to be transformed
     * @return the pipelined action
     */
    public static <T1, T2> BiConsumer<T1, T2> pipeline(BiConsumer<T1, T2> action) {
        return new PipelinedBinaryAction<T1, T2>(Iterations.iterable(action));
    }

    /**
     * Creates a pipeline from two binary actions.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param former the former action
     * @param latter the latter action
     * @return the pipelined action
     */
    public static <T1, T2> BiConsumer<T1, T2> pipeline(BiConsumer<T1, T2> former, BiConsumer<T1, T2> latter) {
        return new PipelinedBinaryAction<T1, T2>(Iterations.iterable(former, latter));
    }

    /**
     * Creates a pipeline from three binary actions.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param first the first action
     * @param second the second action
     * @param third the third action
     * @return the pipelined action
     */
    public static <T1, T2> BiConsumer<T1, T2> pipeline(BiConsumer<T1, T2> first, BiConsumer<T1, T2> second, BiConsumer<T1, T2> third) {
        return new PipelinedBinaryAction<T1, T2>(Iterations.iterable(first, second, third));
    }

    /**
     * Creates a pipeline from an array of binary actions.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param actions the array of actions to be transformed
     * @return the pipelined action
     */
    public static <T1, T2> BiConsumer<T1, T2> pipeline(BiConsumer<T1, T2>... actions) {
        return new PipelinedBinaryAction<T1, T2>(Iterations.iterable(actions));
    }

    /**
     * Creates a pipeline from a ternary action.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param action the action to be transformed
     * @return the pipelined action
     */
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> pipeline(TernaryAction<T1, T2, T3> action) {
        return new PipelinedTernaryAction<T1, T2, T3>(Iterations.iterable(action));
    }

    /**
     * Creates a pipeline from two ternary actions.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param former the former action
     * @param latter the latter action
     * @return the pipelined action
     */
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> pipeline(TernaryAction<T1, T2, T3> former, TernaryAction<T1, T2, T3> latter) {
        return new PipelinedTernaryAction<T1, T2, T3>(Iterations.iterable(former, latter));
    }

    /**
     * Creates a pipeline from three ternary actions.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param first the first action
     * @param second the second action
     * @param third the third action
     * @return the pipelined action
     */
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> pipeline(TernaryAction<T1, T2, T3> first, TernaryAction<T1, T2, T3> second, TernaryAction<T1, T2, T3> third) {
        return new PipelinedTernaryAction<T1, T2, T3>(Iterations.iterable(first, second, third));
    }

    /**
     * Creates a pipeline from an array of ternary actions.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param actions the array of actions to be transformed
     * @return the pipelined action
     */
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> pipeline(TernaryAction<T1, T2, T3>... actions) {
        return new PipelinedTernaryAction<T1, T2, T3>(Iterations.iterable(actions));
    }
}