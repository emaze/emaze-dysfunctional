package net.emaze.dysfunctional.dispatching.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ReflectiveActionTest {

    @Test
    public void canPerformReflectiveAction() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final ReflectiveAction<List<Integer>> action = new ReflectiveAction<List<Integer>>();
        action.setCallee(this);
        action.setMethodName("mockMethod");
        action.setMessageClass(List.class);
        action.perform(bucket);
        Assert.assertEquals(1, bucket.size());
    }
    
    @Test
    public void canPerformReflectiveActionByName() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final ReflectiveAction<List<Integer>> action = new ReflectiveAction<List<Integer>>();
        action.setCallee(this);
        action.setMethodName("mockMethod");
        action.perform(bucket);
        Assert.assertEquals(1, bucket.size());
    }

    @Test
    public void canPerformReflectiveActionMultipleTimes() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final ReflectiveAction<List<Integer>> action = new ReflectiveAction<List<Integer>>();
        action.setCallee(this);
        action.setMethodName("mockMethod");
        action.setMessageClass(List.class);
        action.perform(bucket);
        action.perform(bucket);
        Assert.assertEquals(2, bucket.size());
    }

    @Test
    public void canChangeMethodName() {
        final List<Integer> bucket = new ArrayList<Integer>();

        final ReflectiveAction<List<Integer>> action = new ReflectiveAction<List<Integer>>();
        action.setCallee(this);
        action.setMethodName("mockMethod");
        action.setMessageClass(List.class);

        action.perform(bucket);

        action.setMethodName("anotherMockMethod");
        action.perform(bucket);

        Assert.assertEquals(bucket, Arrays.asList(1, 2));
    }

    @Test
    public void canChangeMessageClass() {
        final List<Integer> bucket = new ArrayList<Integer>();

        final ReflectiveAction<List<Integer>> action = new ReflectiveAction<List<Integer>>();
        action.setCallee(this);
        action.setMethodName("mockMethod");
        action.setMessageClass(List.class);

        action.perform(bucket);

        action.setMessageClass(List.class);
        action.perform(bucket);

        Assert.assertEquals(bucket, Arrays.asList(1, 1));
    }

    @Test
    public void canChangeCallee() {
        final List<Integer> bucket = new ArrayList<Integer>();

        final ReflectiveAction<List<Integer>> action = new ReflectiveAction<List<Integer>>();
        action.setCallee(this);
        action.setMethodName("mockMethod");
        action.setMessageClass(List.class);

        action.perform(bucket);

        action.setCallee(this);
        action.perform(bucket);

        Assert.assertEquals(bucket, Arrays.asList(1, 1));
    }
    
    @Test
    public void canCallSameReflectiveActionTwoTimes() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final ReflectiveAction<List<Integer>> action = new ReflectiveAction<List<Integer>>();
        action.setCallee(this);
        action.setMethodName("mockMethod");
        action.setMessageClass(List.class);
        action.perform(bucket);
        action.perform(bucket);
        Assert.assertEquals(bucket, Arrays.asList(1, 1));
    }

    public void mockMethod(List<Integer> bucket) {
        bucket.add(1);
    }

    public void anotherMockMethod(List<Integer> bucket) {
        bucket.add(2);
    }
}
