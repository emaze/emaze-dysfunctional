package net.emaze.disfunctional;

/**
 *
 * @author rferranti
 */
public class Iterations {

    public static <E> boolean any(Iterable<E> iterable, Predicate<E> predicate){
        for(E element: iterable){
            if(predicate.match(element)){
                return true;
            }
        }
        return false;
    }
    
    public static <E> boolean every(Iterable<E> iterable, Predicate<E> predicate){
        for(E element: iterable){
            if(!predicate.match(element)){
                return false;
            }
        }
        return true;
    }
}
