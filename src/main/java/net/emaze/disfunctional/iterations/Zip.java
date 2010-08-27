package net.emaze.disfunctional.iterations;

/**
 *
 * @author rferranti
 */
public class Zip {

    public <T1, T2> ZipShortestIterator<T1, T2> shortest(Iterable<T1> former, Iterable<T2> latter) {
        return new ZipShortestIterator(former.iterator(), latter.iterator());
    }

    public <T1, T2> ZipLongestIterator<T1, T2> longest(Iterable<T1> former, Iterable<T2> latter) {
        return new ZipLongestIterator(former.iterator(), latter.iterator());
    }

    public <T1, T2> ZipShortestIterator<T1, Integer> counted(Iterable<T1> former) {
        return new ZipShortestIterator(former.iterator(), new CountingIterator(0, Integer.MAX_VALUE));
    }
    
    public <T1, T2> ZipLongestIterator<T1, Integer> countedl(Iterable<T1> former) {
        return new ZipLongestIterator(former.iterator(), new CountingIterator(0, Integer.MAX_VALUE));
    }
}
