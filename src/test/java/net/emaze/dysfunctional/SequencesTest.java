package net.emaze.dysfunctional;

import java.util.Iterator;
import java.util.stream.Stream;
import net.emaze.dysfunctional.sequences.Sequence;
import org.junit.Assert;
import org.junit.Test;

public class SequencesTest {

    @Test
    public void canCreateAnEmptySequence() {
        final Sequence<?> empty = Sequences.empty();
        Assert.assertEquals(0, empty.count());
    }

    @Test
    public void canCreateASequenceFromAnArrayOfElements() {
        final Sequence<?> s = Sequences.of(1, 2, 3, 4);
        Assert.assertEquals(4, s.count());
    }

    @Test
    public void canCreateASequenceFromAnIterator() {
        final Iterator<Integer> iterator = Iterations.iterator(1, 2, 3, 4);
        final Sequence<?> s = Sequences.of(iterator);
        Assert.assertEquals(4, s.count());
    }

    @Test
    public void canCreateASequenceFromAnIterable() {
        final Iterable<Integer> iterator = Iterations.iterable(1, 2, 3, 4);
        final Sequence<?> s = Sequences.of(iterator);
        Assert.assertEquals(4, s.count());
    }

    @Test
    public void canCreateASequenceFromAStream() {
        final Stream<Integer> stream = Stream.<Integer>builder().add(1).add(2).add(3).add(4).build();
        final Sequence<?> s = Sequences.of(stream);
        Assert.assertEquals(4, s.count());
    }

}
