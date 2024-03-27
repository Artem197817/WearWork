package workwear.workwearclient.model.modelview;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WearIssuedView {

    private Long employeeId;
    private Long workWearId;
    private Integer monthPeriod;
}
