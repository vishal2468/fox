package ai.vishal.fox.model.request;

import java.util.List;

import ai.vishal.fox.model.dto.AggregateBy;
import ai.vishal.fox.model.dto.BucketByTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BloodPressure {
    List<AggregateBy> aggregateBy;
    BucketByTime bucketByTime;
    long startTimeMillis;
    long endTimeMillis;
}
