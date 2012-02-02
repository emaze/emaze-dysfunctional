package net.emaze.dysfunctional.samples;

import java.util.Iterator;
import net.emaze.dysfunctional.Filtering;
import net.emaze.dysfunctional.Multiplexing;
import net.emaze.dysfunctional.Strings;
import net.emaze.dysfunctional.iterations.ConstantIterator;
import org.junit.Test;

public class BatmanIteratorTest {

    @Test
    public void originalBatmanThemeFromInfiniteIterators() {
        final Iterator<Character> n = new ConstantIterator<Character>('N');
        final Iterator<Character> a = new ConstantIterator<Character>('a');
        final Iterator<Character> nanana = Filtering.take(1000, Multiplexing.roundrobin(n, a));
        System.out.format("%s BATMAN!%n", Strings.join(nanana));
    }
}
