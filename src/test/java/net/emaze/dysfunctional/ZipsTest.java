package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.NextIntegerSequencingPolicy;
import net.emaze.dysfunctional.ranges.DenseRange;
import net.emaze.dysfunctional.ranges.Range.Endpoint;
import net.emaze.dysfunctional.order.JustBeforeNothingComparator;
import net.emaze.dysfunctional.ranges.Range;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 * if you are going to add a test here, consider that Zips should be just a thin
 * facade, and tests on ZipsTest should be just "smoke tests"
 *
 * @author rferranti
 */
public class ZipsTest {

    public static Pair<Integer, Integer> p(int f, int l) {
        return Pair.of(f, l);
    }

    public static Pair<Maybe<Integer>, Maybe<Integer>> p(Maybe<Integer> f, Maybe<Integer> l) {
        return Pair.of(f, l);
    }

    @Test
    public void canZipShortestWithIterators() {
        final List<Integer> former = Arrays.asList(1, 3);
        final List<Integer> latter = Arrays.asList(2, 4, 5);
        final Iterator<Pair<Integer, Integer>> got = Zips.shortest(former.iterator(), latter.iterator());
        Assert.assertEquals(Arrays.asList(p(1, 2), p(3, 4)), Consumers.all(got));
    }

    @Test
    public void canZipShortestWithArrays() {
        final Integer[] former = new Integer[]{1, 3};
        final Integer[] latter = new Integer[]{2, 4, 5};
        final Iterator<Pair<Integer, Integer>> got = Zips.shortest(former, latter);
        Assert.assertEquals(Arrays.asList(p(1, 2), p(3, 4)), Consumers.all(got));
    }

    @Test
    public void canZipShortestWithIterables() {
        final List<Integer> former = Arrays.asList(1, 3);
        final List<Integer> latter = Arrays.asList(2, 4, 5);
        final Iterator<Pair<Integer, Integer>> got = Zips.shortest(former, latter);
        Assert.assertEquals(Arrays.asList(p(1, 2), p(3, 4)), Consumers.all(got));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotZipShortestWithNullFormerIterable() {
        final List<Integer> former = null;
        final List<Integer> latter = Arrays.asList(2, 4, 5);
        Zips.shortest(former, latter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotZipShortestWithNullLatterIterable() {
        final List<Integer> former = Arrays.asList(2, 4, 5);
        final List<Integer> latter = null;
        Zips.shortest(former, latter);
    }

    @Test
    public void canZipLongestWithIterators() {
        final List<Integer> former = Arrays.asList(1, 3);
        final List<Integer> latter = Arrays.asList(2, 4, 5);
        final Iterator<Pair<Maybe<Integer>, Maybe<Integer>>> got = Zips.longest(former.iterator(), latter.iterator());

        final List<Pair<Maybe<Integer>, Maybe<Integer>>> expected = new ArrayList<Pair<Maybe<Integer>, Maybe<Integer>>>();
        expected.add(p(Maybe.just(1), Maybe.just(2)));
        expected.add(p(Maybe.just(3), Maybe.just(4)));
        expected.add(p(Maybe.<Integer>nothing(), Maybe.just(5)));

        Assert.assertEquals(expected, Consumers.all(got));
    }

    @Test
    public void canZipLongestWithArrays() {
        final Integer[] former = new Integer[]{1, 3};
        final Integer[] latter = new Integer[]{2, 4, 5};
        final Iterator<Pair<Maybe<Integer>, Maybe<Integer>>> got = Zips.longest(former, latter);

        final List<Pair<Maybe<Integer>, Maybe<Integer>>> expected = new ArrayList<Pair<Maybe<Integer>, Maybe<Integer>>>();
        expected.add(p(Maybe.just(1), Maybe.just(2)));
        expected.add(p(Maybe.just(3), Maybe.just(4)));
        expected.add(p(Maybe.<Integer>nothing(), Maybe.just(5)));

        Assert.assertEquals(expected, Consumers.all(got));
    }

    @Test
    public void canZipLongestWithIterables() {
        final List<Integer> former = Arrays.asList(1, 3);
        final List<Integer> latter = Arrays.asList(2, 4, 5);
        Iterator<Pair<Maybe<Integer>, Maybe<Integer>>> got = Zips.longest(former, latter);

        final List<Pair<Maybe<Integer>, Maybe<Integer>>> expected = new ArrayList<Pair<Maybe<Integer>, Maybe<Integer>>>();
        expected.add(p(Maybe.just(1), Maybe.just(2)));
        expected.add(p(Maybe.just(3), Maybe.just(4)));
        expected.add(p(Maybe.<Integer>nothing(), Maybe.just(5)));

        Assert.assertEquals(expected, Consumers.all(got));
    }

    @Test(expected = IllegalArgumentException.class)
    public void canZipLongestWithNullFormerIterable() {
        final List<Integer> former = null;
        final List<Integer> latter = Arrays.asList(2, 4, 5);
        Zips.longest(former, latter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canZipLongestWithNullLatterIterable() {
        final List<Integer> former = Arrays.asList(2, 4, 5);
        final List<Integer> latter = null;
        Zips.longest(former, latter);
    }

    @Test
    public void canMakeAnIterableCounted() {
        final Iterable<String> bucket = Iterations.iterable("a", "b");
        final Iterator<Pair<Integer, String>> iterator = Zips.counted(bucket);
        final Iterator<Pair<Integer, String>> expected = Iterations.iterator(Pair.of(0, "a"), Pair.of(1, "b"));
        Assert.assertEquals(Consumers.all(expected), Consumers.all(iterator));
    }

    @Test
    public void canMakeAnIterableCountedWithRange() {
        final Range<Integer> range = new DenseRange<Integer>(new NextIntegerSequencingPolicy(), new JustBeforeNothingComparator<Integer>(new ComparableComparator<Integer>()), Endpoint.Include, 1, Maybe.just(10), Endpoint.Include);
        final Iterable<String> bucket = Iterations.iterable("a", "b");
        final Iterator<Pair<Integer, String>> iterator = Zips.counted(bucket, range);
        final Iterator<Pair<Integer, String>> expected = Iterations.iterator(Pair.of(1, "a"), Pair.of(2, "b"));
        Assert.assertEquals(Consumers.all(expected), Consumers.all(iterator));
    }

    @Test
    public void canMakeAnIteratorCounted() {
        final Iterator<String> bucket = Iterations.iterator("a", "b");
        final Iterator<Pair<Integer, String>> iterator = Zips.counted(bucket);
        final Iterator<Pair<Integer, String>> expected = Iterations.iterator(Pair.of(0, "a"), Pair.of(1, "b"));
        Assert.assertEquals(Consumers.all(expected), Consumers.all(iterator));
    }

    @Test
    public void canMakeAnIteratorCountedWithRange() {
        final Range<Integer> range = new DenseRange<Integer>(new NextIntegerSequencingPolicy(), new JustBeforeNothingComparator<Integer>(new ComparableComparator<Integer>()), Endpoint.Include, 1, Maybe.just(10), Endpoint.Include);
        final Iterator<String> bucket = Iterations.iterator("a", "b");
        final Iterator<Pair<Integer, String>> iterator = Zips.counted(bucket, range);
        final Iterator<Pair<Integer, String>> expected = Iterations.iterator(Pair.of(1, "a"), Pair.of(2, "b"));
        Assert.assertEquals(Consumers.all(expected), Consumers.all(iterator));
    }

    @Test
    public void canMakeAnArrayCounted() {
        final String[] bucket = {"a", "b"};
        final Iterator<Pair<Integer, String>> iterator = Zips.counted(bucket);
        final Iterator<Pair<Integer, String>> expected = Iterations.iterator(Pair.of(0, "a"), Pair.of(1, "b"));
        Assert.assertEquals(Consumers.all(expected), Consumers.all(iterator));
    }

    @Test
    public void canMakeAnArrayCountedWithRange() {
        final Range<Integer> range = new DenseRange<Integer>(new NextIntegerSequencingPolicy(), new JustBeforeNothingComparator<Integer>(new ComparableComparator<Integer>()), Endpoint.Include, 1, Maybe.just(10), Endpoint.Include);
        final String[] bucket = {"a", "b"};
        final Iterator<Pair<Integer, String>> iterator = Zips.counted(bucket, range);
        final Iterator<Pair<Integer, String>> expected = Iterations.iterator(Pair.of(1, "a"), Pair.of(2, "b"));
        Assert.assertEquals(Consumers.all(expected), Consumers.all(iterator));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallCountedWithANullIterable() {
        final Iterable<Object> iterable = null;
        Zips.counted(iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallCountedWithANullIterableAndARange() {
        final Iterable<Object> iterable = null;
        final StubRange range = new StubRange();
        Zips.counted(iterable, range);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallCountedWithANullArray() {
        final Object[] iterable = null;
        Zips.counted(iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallCountedWithANullArrayAndARange() {
        final Object[] iterable = null;
        final StubRange range = new StubRange();
        Zips.counted(iterable, range);
    }

    private static class StubRange implements Range<Object> {

        @Override
        public boolean contains(Object element) {
            return false;
        }

        @Override
        public boolean overlaps(Range<Object> rhs) {
            return false;
        }

        @Override
        public Object begin() {
            return null;
        }

        @Override
        public Maybe<Object> end() {
            return null;
        }

        @Override
        public List<DenseRange<Object>> densified() {
            return null;
        }

        @Override
        public Iterator<Object> iterator() {
            return null;
        }

        @Override
        public int compareTo(Range<Object> o) {
            return 0;
        }
    }

    @Test
    public void facadeIsNotFinal() {
        new Zips() {
        };
    }
}
