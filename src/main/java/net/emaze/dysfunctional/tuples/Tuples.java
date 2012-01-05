package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

public abstract class Tuples {

    public static <R, T, U> Delegate<R, Pair<T, U>> tupled(BinaryDelegate<R, T, U> delegate) {
        return new BinaryToUnaryDelegate<R, T, U>(delegate);
    }

    public static <T, U> Predicate<Pair<T, U>> tupled(BinaryPredicate<T, U> predicate) {
        return new BinaryToUnaryPredicate<T, U>(predicate);
    }

    public static <T, U> Action<Pair<T, U>> tupled(BinaryAction<T, U> predicate) {
        return new BinaryToUnaryAction<T, U>(predicate);
    }

    public static <R, T, U, V> Delegate<R, Triple<T, U, V>> tupled(TernaryDelegate<R, T, U, V> delegate) {
        return new TernaryToUnaryDelegate<R, T, U, V>(delegate);
    }

    public static <T, U, V> Predicate<Triple<T, U, V>> tupled(TernaryPredicate<T, U, V> predicate) {
        return new TernaryToUnaryPredicate<T, U, V>(predicate);
    }

    public static <T, U, V> Action<Triple<T, U, V>> tupled(TernaryAction<T, U, V> predicate) {
        return new TernaryToUnaryAction<T, U, V>(predicate);
    }

    public static abstract class Pairs {

        public static <R, T, U> BinaryDelegate<R, T, U> untupled(Delegate<R, Pair<T, U>> delegate) {
            return new UnaryToBinaryDelegate<R, T, U>(delegate);
        }

        public static <T, U> BinaryPredicate<T, U> untupled(Predicate<Pair<T, U>> predicate) {
            return new UnaryToBinaryPredicate<T, U>(predicate);
        }

        public static <T, U> BinaryAction<T, U> untupled(Action<Pair<T, U>> predicate) {
            return new UnaryToBinaryAction<T, U>(predicate);
        }
    }

    public static abstract class Triples {

        public static <R, T, U, V> TernaryDelegate<R, T, U, V> untupled(Delegate<R, Triple<T, U, V>> delegate) {
            return new UnaryToTernaryDelegate<R, T, U, V>(delegate);
        }

        public static <T, U, V> TernaryPredicate<T, U, V> untupled(Predicate<Triple<T, U, V>> predicate) {
            return new UnaryToTernaryPredicate<T, U, V>(predicate);
        }

        public static <T, U, V> TernaryAction<T, U, V> untupled(Action<Triple<T, U, V>> predicate) {
            return new UnaryToTernaryAction<T, U, V>(predicate);
        }
    }
}
