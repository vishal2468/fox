package ai.vishal.fox.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class BucketByTime{
    long durationMillis;
}
