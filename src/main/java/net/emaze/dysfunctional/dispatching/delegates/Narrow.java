package net.emaze.dysfunctional.dispatching.delegates;

/**
 * i.e: Provider<Set<Integer>> compose = Dispatching.compose(new Narrow<Set<Integer>, HashSet<Integer>>(), new HashSetFactory<Integer>());
 * @author rferranti
 * @param <R>
 * @param <E> 
 */
public class Narrow<R, E extends R> implements Delegate<R, E> {

    @Override
    public R perform(E element) {
        return element;
    }
}
