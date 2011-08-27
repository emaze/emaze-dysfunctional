package net.emaze.dysfunctional.dispatching.actions;

import java.util.Arrays;
import java.util.Collections;

public abstract class Pipelines {

    public static <T> PipelinedAction<T> pipeline(Action<T> action) {
        return new PipelinedAction<T>(Collections.singletonList(action));
    }

    public static <T> PipelinedAction<T> pipeline(Action<T> former, Action<T> latter) {
        return new PipelinedAction<T>(Arrays.asList(former, latter));
    }

    public static <T> PipelinedAction<T> pipeline(Action<T> first, Action<T> second, Action<T> third) {
        return new PipelinedAction<T>(Arrays.asList(first, second, third));
    }

    public static <T> PipelinedAction<T> pipeline(Action<T>... actions) {
        return new PipelinedAction<T>(Arrays.asList(actions));
    }

    public static <T1, T2> PipelinedBinaryAction<T1, T2> pipeline(BinaryAction<T1, T2> action) {
        return new PipelinedBinaryAction<T1, T2>(Collections.singletonList(action));
    }

    public static <T1, T2> PipelinedBinaryAction<T1, T2> pipeline(BinaryAction<T1, T2> former, BinaryAction<T1, T2> latter) {
        return new PipelinedBinaryAction<T1, T2>(Arrays.asList(former, latter));
    }

    public static <T1, T2> PipelinedBinaryAction<T1, T2> pipeline(BinaryAction<T1, T2> first, BinaryAction<T1, T2> second, BinaryAction<T1, T2> third) {
        return new PipelinedBinaryAction<T1, T2>(Arrays.asList(first, second, third));
    }

    public static <T1, T2> PipelinedBinaryAction<T1, T2> pipeline(BinaryAction<T1, T2>... actions) {
        return new PipelinedBinaryAction<T1, T2>(Arrays.asList(actions));
    }

    public static <T1, T2, T3> PipelinedTernaryAction<T1, T2, T3> pipeline(TernaryAction<T1, T2, T3> action) {
        return new PipelinedTernaryAction<T1, T2, T3>(Collections.singletonList(action));
    }

    public static <T1, T2, T3> PipelinedTernaryAction<T1, T2, T3> pipeline(TernaryAction<T1, T2, T3> former, TernaryAction<T1, T2, T3> latter) {
        return new PipelinedTernaryAction<T1, T2, T3>(Arrays.asList(former, latter));
    }

    public static <T1, T2, T3> PipelinedTernaryAction<T1, T2, T3> pipeline(TernaryAction<T1, T2, T3> first, TernaryAction<T1, T2, T3> second, TernaryAction<T1, T2, T3> third) {
        return new PipelinedTernaryAction<T1, T2, T3>(Arrays.asList(first, second, third));
    }

    public static <T1, T2, T3> PipelinedTernaryAction<T1, T2, T3> pipeline(TernaryAction<T1, T2, T3>... actions) {
        return new PipelinedTernaryAction<T1, T2, T3>(Arrays.asList(actions));
    }
}