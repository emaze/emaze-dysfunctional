package net.emaze.dysfunctional.convolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 * if you are going to add a test here, consider that Zips should be just
 * a thin facade, and tests on ZipsTest should be just "smoke tests"
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
        List<Integer> former = Arrays.asList(1, 3);
        List<Integer> latter = Arrays.asList(2, 4, 5);
        Iterable<Pair<Integer, Integer>> got = Zips.shortest(former.iterator(), latter.iterator());
        Assert.assertEquals(Arrays.asList(p(1, 2), p(3, 4)), Consumers.all(got.iterator()));
    }

    @Test
    public void canZipShortestWithArrays() {
        Integer[] former = new Integer[]{1, 3};
        Integer[] latter = new Integer[]{2, 4, 5};
        Iterable<Pair<Integer, Integer>> got = Zips.shortest(former, latter);
        Assert.assertEquals(Arrays.asList(p(1, 2), p(3, 4)), Consumers.all(got.iterator()));
    }

    @Test
    public void canZipShortestWithIterables() {
        List<Integer> former = Arrays.asList(1, 3);
        List<Integer> latter = Arrays.asList(2, 4, 5);
        Iterable<Pair<Integer, Integer>> got = Zips.shortest(former, latter);
        Assert.assertEquals(Arrays.asList(p(1, 2), p(3, 4)), Consumers.all(got.iterator()));
    }

    @Test
    public void canZipLongestWithIterators() {
        List<Integer> former = Arrays.asList(1, 3);
        List<Integer> latter = Arrays.asList(2, 4, 5);
        Iterable<Pair<Maybe<Integer>, Maybe<Integer>>> got = Zips.longest(former.iterator(), latter.iterator());

        List<Pair<Maybe<Integer>, Maybe<Integer>>> expected = new ArrayList<Pair<Maybe<Integer>, Maybe<Integer>>>();
        expected.add(p(Maybe.just(1), Maybe.just(2)));
        expected.add(p(Maybe.just(3), Maybe.just(4)));
        expected.add(p(Maybe.<Integer>nothing(), Maybe.just(5)));

        Assert.assertEquals(expected, Consumers.all(got.iterator()));
    }

    @Test
    public void canZipLongestWithArrays() {
        Integer[] former = new Integer[]{1, 3};
        Integer[] latter = new Integer[]{2, 4, 5};
        Iterable<Pair<Maybe<Integer>, Maybe<Integer>>> got = Zips.longest(former, latter);

        List<Pair<Maybe<Integer>, Maybe<Integer>>> expected = new ArrayList<Pair<Maybe<Integer>, Maybe<Integer>>>();
        expected.add(p(Maybe.just(1), Maybe.just(2)));
        expected.add(p(Maybe.just(3), Maybe.just(4)));
        expected.add(p(Maybe.<Integer>nothing(), Maybe.just(5)));

        Assert.assertEquals(expected, Consumers.all(got.iterator()));
    }

    @Test
    public void canZipLongestWithIterables() {
        List<Integer> former = Arrays.asList(1, 3);
        List<Integer> latter = Arrays.asList(2, 4, 5);
        Iterable<Pair<Maybe<Integer>, Maybe<Integer>>> got = Zips.longest(former, latter);

        List<Pair<Maybe<Integer>, Maybe<Integer>>> expected = new ArrayList<Pair<Maybe<Integer>, Maybe<Integer>>>();
        expected.add(p(Maybe.just(1), Maybe.just(2)));
        expected.add(p(Maybe.just(3), Maybe.just(4)));
        expected.add(p(Maybe.<Integer>nothing(), Maybe.just(5)));

        Assert.assertEquals(expected, Consumers.all(got.iterator()));
    }
}
