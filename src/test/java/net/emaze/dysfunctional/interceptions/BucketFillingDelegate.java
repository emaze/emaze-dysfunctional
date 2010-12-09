package net.emaze.dysfunctional.interceptions;

import java.util.List;
import net.emaze.dysfunctional.delegates.Delegate;

public class BucketFillingDelegate implements Delegate<String, String> {

    private final int id;
    private final List<Integer> bucket;

    public BucketFillingDelegate(int id, List<Integer> bucket) {
        this.id = id;
        this.bucket = bucket;
    }

    @Override
    public String perform(String value) {
        bucket.add(id);
        return value;
    }
}
