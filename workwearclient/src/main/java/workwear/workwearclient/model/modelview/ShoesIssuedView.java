package workwear.workwearclient.model.modelview;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShoesIssuedView {

    private Long employeeId;
    private Long workShoesId;
    private Integer monthPeriod;

}
