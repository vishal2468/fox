package ai.vishal.fox.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CipherResponse {
    private String data;
    private String keyDetails;
}
