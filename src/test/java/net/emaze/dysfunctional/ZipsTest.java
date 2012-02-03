package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.options.Maybe;
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
    public void facadeIsNotFinal() {
        new Zips() {
        };
    }
}
