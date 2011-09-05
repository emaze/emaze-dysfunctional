package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.composing.PipelinedAction;
import net.emaze.dysfunctional.dispatching.composing.PipelinedBinaryAction;
import net.emaze.dysfunctional.dispatching.composing.PipelinedTernaryAction;
import net.emaze.dysfunctional.iterations.Iterations;

public abstract class Pipelines {

    public static <T> PipelinedAction<T> pipeline(Action<T> action) {
        return new PipelinedAction<T>(Iterations.iterable(action));
    }

    public static <T> PipelinedAction<T> pipeline(Action<T> former, Action<T> latter) {
        return new PipelinedAction<T>(Iterations.iterable(former, latter));
    }

    public static <T> PipelinedAction<T> pipeline(Action<T> first, Action<T> second, Action<T> third) {
        return new PipelinedAction<T>(Iterations.iterable(first, second, third));
    }

    public static <T> PipelinedAction<T> pipeline(Action<T>... actions) {
        return new PipelinedAction<T>(Iterations.iterable(actions));
    }

    public static <T1, T2> PipelinedBinaryAction<T1, T2> pipeline(BinaryAction<T1, T2> action) {
        return new PipelinedBinaryAction<T1, T2>(Iterations.iterable(action));
    }

    public static <T1, T2> PipelinedBinaryAction<T1, T2> pipeline(BinaryAction<T1, T2> former, BinaryAction<T1, T2> latter) {
        return new PipelinedBinaryAction<T1, T2>(Iterations.iterable(former, latter));
    }

    public static <T1, T2> PipelinedBinaryAction<T1, T2> pipeline(BinaryAction<T1, T2> first, BinaryAction<T1, T2> second, BinaryAction<T1, T2> third) {
        return new PipelinedBinaryAction<T1, T2>(Iterations.iterable(first, second, third));
    }

    public static <T1, T2> PipelinedBinaryAction<T1, T2> pipeline(BinaryAction<T1, T2>... actions) {
        return new PipelinedBinaryAction<T1, T2>(Iterations.iterable(actions));
    }

    public static <T1, T2, T3> PipelinedTernaryAction<T1, T2, T3> pipeline(TernaryAction<T1, T2, T3> action) {
        return new PipelinedTernaryAction<T1, T2, T3>(Iterations.iterable(action));
    }

    public static <T1, T2, T3> PipelinedTernaryAction<T1, T2, T3> pipeline(TernaryAction<T1, T2, T3> former, TernaryAction<T1, T2, T3> latter) {
        return new PipelinedTernaryAction<T1, T2, T3>(Iterations.iterable(former, latter));
    }

    public static <T1, T2, T3> PipelinedTernaryAction<T1, T2, T3> pipeline(TernaryAction<T1, T2, T3> first, TernaryAction<T1, T2, T3> second, TernaryAction<T1, T2, T3> third) {
        return new PipelinedTernaryAction<T1, T2, T3>(Iterations.iterable(first, second, third));
    }

    public static <T1, T2, T3> PipelinedTernaryAction<T1, T2, T3> pipeline(TernaryAction<T1, T2, T3>... actions) {
        return new PipelinedTernaryAction<T1, T2, T3>(Iterations.iterable(actions));
    }
}