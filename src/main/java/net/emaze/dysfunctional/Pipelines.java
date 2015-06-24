package net.emaze.dysfunctional;

import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.composing.PipelinedConsumer;
import net.emaze.dysfunctional.dispatching.composing.PipelinedBinaryConsumer;
import net.emaze.dysfunctional.dispatching.composing.PipelinedTernaryConsumer;

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
         * @param <T> the consumer parameter type
         * @param actions the iterable to be transformed
         * @return the pipelined consumer
         */
        public static <T> Consumer<T> pipeline(Iterable<Consumer<T>> actions) {
            return new PipelinedConsumer<T>(actions);
        }

        /**
         * Creates a pipeline from an iterator of actions.
         *
         * @param <T> the consumer parameter type
         * @param actions the iterator to be transformed
         * @return the pipelined consumer
         */
        public static <T> Consumer<T> pipeline(Iterator<Consumer<T>> actions) {
            return new PipelinedConsumer<T>(Consumers.all(actions));
        }
    }

    /**
     * pipeline.
     */
    public abstract static class Binary {

        /**
         * Creates a pipeline from an iterable of binary actions.
         *
         * @param <T1> the consumer first parameter type
         * @param <T2> the consumer second parameter type
         * @param actions the iterable to be transformed
         * @return the pipelined consumer
         */
        public static <T1, T2> BiConsumer<T1, T2> pipeline(Iterable<BiConsumer<T1, T2>> actions) {
            return new PipelinedBinaryConsumer<T1, T2>(actions);
        }

        /**
         * Creates a pipeline from an iterator of binary actions.
         *
         * @param <T1> the consumer first parameter type
         * @param <T2> the consumer second parameter type
         * @param actions the iterator to be transformed
         * @return the pipelined consumer
         */
        public static <T1, T2> BiConsumer<T1, T2> pipeline(Iterator<BiConsumer<T1, T2>> actions) {
            return new PipelinedBinaryConsumer<T1, T2>(Consumers.all(actions));
        }
    }

    /**
     * pipeline.
     */
    public abstract static class Ternary {

        /**
         * Creates a pipeline from an iterable of ternary actions.
         *
         * @param <T1> the consumer first parameter type
         * @param <T2> the consumer second parameter type
         * @param <T3> the consumer third parameter type
         * @param actions the iterable to be transformed
         * @return the pipelined consumer
         */
        public static <T1, T2, T3> TriConsumer<T1, T2, T3> pipeline(Iterable<TriConsumer<T1, T2, T3>> actions) {
            return new PipelinedTernaryConsumer<T1, T2, T3>(actions);
        }

        /**
         * Creates a pipeline from an iterator of ternary actions.
         *
         * @param <T1> the consumer first parameter type
         * @param <T2> the consumer second parameter type
         * @param <T3> the consumer third parameter type
         * @param actions the iterator to be transformed
         * @return the pipelined consumer
         */
        public static <T1, T2, T3> TriConsumer<T1, T2, T3> pipeline(Iterator<TriConsumer<T1, T2, T3>> actions) {
            return new PipelinedTernaryConsumer<T1, T2, T3>(Consumers.all(actions));
        }
    }

    /**
     * Creates a pipeline from an consumer.
     *
     * @param <T> the consumer parameter type
     * @param consumer the consumer to be transformed
     * @return the pipelined consumer
     */
    public static <T> Consumer<T> pipeline(Consumer<T> consumer) {
        return new PipelinedConsumer<T>(Iterations.iterable(consumer));
    }

    /**
     * Creates a pipeline from two actions.
     *
     * @param <T> the consumer parameter type
     * @param former the former consumer
     * @param latter the latter consumer
     * @return the pipelined consumer
     */
    public static <T> Consumer<T> pipeline(Consumer<T> former, Consumer<T> latter) {
        return new PipelinedConsumer<T>(Iterations.iterable(former, latter));
    }

    /**
     * Creates a pipeline from three actions.
     *
     * @param <T> the consumer parameter type
     * @param first the first consumer
     * @param second the second consumer
     * @param third the third consumer
     * @return the pipelined consumer
     */
    public static <T> Consumer<T> pipeline(Consumer<T> first, Consumer<T> second, Consumer<T> third) {
        return new PipelinedConsumer<T>(Iterations.iterable(first, second, third));
    }

    /**
     * Creates a pipeline from an array of actions.
     *
     * @param <T> the consumer parameter type
     * @param actions the array of actions to be transformed
     * @return the pipelined consumer
     */
    public static <T> Consumer<T> pipeline(Consumer<T>... actions) {
        return new PipelinedConsumer<T>(Iterations.iterable(actions));
    }

    /**
     * Creates a pipeline from a binary consumer.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param consumer the consumer to be transformed
     * @return the pipelined consumer
     */
    public static <T1, T2> BiConsumer<T1, T2> pipeline(BiConsumer<T1, T2> consumer) {
        return new PipelinedBinaryConsumer<T1, T2>(Iterations.iterable(consumer));
    }

    /**
     * Creates a pipeline from two binary actions.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param former the former consumer
     * @param latter the latter consumer
     * @return the pipelined consumer
     */
    public static <T1, T2> BiConsumer<T1, T2> pipeline(BiConsumer<T1, T2> former, BiConsumer<T1, T2> latter) {
        return new PipelinedBinaryConsumer<T1, T2>(Iterations.iterable(former, latter));
    }

    /**
     * Creates a pipeline from three binary actions.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param first the first consumer
     * @param second the second consumer
     * @param third the third consumer
     * @return the pipelined consumer
     */
    public static <T1, T2> BiConsumer<T1, T2> pipeline(BiConsumer<T1, T2> first, BiConsumer<T1, T2> second, BiConsumer<T1, T2> third) {
        return new PipelinedBinaryConsumer<T1, T2>(Iterations.iterable(first, second, third));
    }

    /**
     * Creates a pipeline from an array of binary actions.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param actions the array of actions to be transformed
     * @return the pipelined consumer
     */
    public static <T1, T2> BiConsumer<T1, T2> pipeline(BiConsumer<T1, T2>... actions) {
        return new PipelinedBinaryConsumer<T1, T2>(Iterations.iterable(actions));
    }

    /**
     * Creates a pipeline from a ternary consumer.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param consumer the consumer to be transformed
     * @return the pipelined consumer
     */
    public static <T1, T2, T3> TriConsumer<T1, T2, T3> pipeline(TriConsumer<T1, T2, T3> consumer) {
        return new PipelinedTernaryConsumer<T1, T2, T3>(Iterations.iterable(consumer));
    }

    /**
     * Creates a pipeline from two ternary actions.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param former the former consumer
     * @param latter the latter consumer
     * @return the pipelined consumer
     */
    public static <T1, T2, T3> TriConsumer<T1, T2, T3> pipeline(TriConsumer<T1, T2, T3> former, TriConsumer<T1, T2, T3> latter) {
        return new PipelinedTernaryConsumer<T1, T2, T3>(Iterations.iterable(former, latter));
    }

    /**
     * Creates a pipeline from three ternary actions.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param first the first consumer
     * @param second the second consumer
     * @param third the third consumer
     * @return the pipelined consumer
     */
    public static <T1, T2, T3> TriConsumer<T1, T2, T3> pipeline(TriConsumer<T1, T2, T3> first, TriConsumer<T1, T2, T3> second, TriConsumer<T1, T2, T3> third) {
        return new PipelinedTernaryConsumer<T1, T2, T3>(Iterations.iterable(first, second, third));
    }

    /**
     * Creates a pipeline from an array of ternary actions.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param actions the array of actions to be transformed
     * @return the pipelined consumer
     */
    public static <T1, T2, T3> TriConsumer<T1, T2, T3> pipeline(TriConsumer<T1, T2, T3>... actions) {
        return new PipelinedTernaryConsumer<T1, T2, T3>(Iterations.iterable(actions));
    }
}