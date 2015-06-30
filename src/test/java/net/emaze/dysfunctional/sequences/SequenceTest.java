package net.emaze.dysfunctional.sequences;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.Maps;
import net.emaze.dysfunctional.Sequences;
import net.emaze.dysfunctional.options.Box;
import org.junit.Assert;
import org.junit.Test;

public class SequenceTest {

    @Test
    public void canCreateSequenceFromIterator() {
        final Iterator<Integer> iterator = Iterations.iterator(1, 2, 3, 4);
        final Sequence<Integer> s = Sequence.from(iterator);
        Assert.assertEquals(4, s.count());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfIteratorIsNull() {
        final Iterator<Integer> iterator = null;
        final Sequence<Integer> s = Sequence.from(iterator);
        Assert.assertEquals(4, s.count());
    }

    @Test
    public void canCreateSequenceFromStream() {
        final Stream<Integer> stream = Stream.<Integer>builder().add(1).add(2).add(3).add(4).build();
        final Sequence<Integer> s = Sequence.from(stream);
        Assert.assertEquals(4, s.count());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfStreamIsNull() {
        final Stream<Integer> stream = null;
        final Sequence<Integer> s = Sequence.from(stream);
        Assert.assertEquals(4, s.count());
    }

    @Test
    public void canConsumeToList() {
        final Iterator<Integer> iterator = Iterations.iterator(1, 2, 3, 4);
        final List<Integer> got = Sequence.from(iterator).toList();
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), got);
    }

    @Test
    public void canConsumeToSet() {
        final Iterator<Integer> iterator = Iterations.iterator(1, 2, 3, 4);
        final Set<Integer> got = Sequence.from(iterator).toSet();
        Assert.assertEquals(new HashSet<>(Arrays.asList(2, 1, 4, 3)), got);
    }

    @Test
    public void canConsumeToMap() {
        final Iterator<Integer> iterator = Iterations.iterator(1, 2, 3, 4);
        final Map<Integer, Integer> got = Sequence.from(iterator).toMap(Function.identity(), v -> v * 2);
        final Map<Integer, Integer> expected = Maps.<Integer, Integer>builder()
                .add(1, 2)
                .add(2, 4)
                .add(3, 6)
                .add(4, 8)
                .toMap();
        Assert.assertEquals(expected, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfMapUsingANullKeyMapperFunction() {
        Sequences.empty().toMap(null, Function.identity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfMapUsingANullValueMapperFunction() {
        Sequences.empty().toMap(Function.identity(), null);
    }

    @Test
    public void canTakeDistinctObject() {
        final List<Box<String>> got = Sequences.of(Box.of("a"), Box.of("a"), Box.of("b"), Box.of("a"), Box.of("a"), Box.of("b")).distinctBy(Function.identity()).toList();
        Assert.assertEquals(Arrays.asList(Box.of("a"), Box.of("b")), got);
    }

    @Test
    public void canTakeObjectWithDistinctValuesOfAProperty() {
        final List<Box<String>> got = Sequences.of(Box.of("a"), Box.of("a"), Box.of("b")).distinctBy(k -> k.getContent()).toList();
        Assert.assertEquals(Arrays.asList(Box.of("a"), Box.of("b")), got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfDistinctByUsingANullPropertyMapperFunction() {
        Sequences.empty().distinctBy(null);
    }

    @Test
    public void canTakeFirstElementOfTheSequence() {
        final Integer got = Sequences.of(1, 2, 3, 4).first();
        Assert.assertEquals(new Integer(1), got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfTryToTakeFirstElementOfEmptySequence() {
        Sequences.empty().first();
    }
}
