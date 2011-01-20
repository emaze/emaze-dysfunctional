
package net.emaze.dysfunctional.delegates;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AllMatchingPredicateTest {

    public AllMatchingPredicateTest() {
    }

    @Test(expected=IllegalArgumentException.class)
    public void cannotEvaluateEmptyPredicateList() {
        AllMatchingPredicate<String> test = new AllMatchingPredicate<String>();
        test.test("A");
    }

    @Test
    public void canEvaluateAllTruePredicate() {
        AllMatchingPredicate<String> test = new AllMatchingPredicate<String>();
        test.add(new Always<String>());
        test.add(new Always<String>());
        assertTrue(test.test("aaa"));
    }

    @Test
    public void canEvaluateNotAllTruePredicate() {
        AllMatchingPredicate<String> test = new AllMatchingPredicate<String>();
        test.add(new Never<String>());
        test.add(new Always<String>());
        assertFalse(test.test("aaa"));
    }

}