package workwear.workwearclient.model.modelview;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WorkWearIssuedView {


    private String modelWorkWear;
    private String workWearType;
    private String workWearSize;
    private String workWearHeight;
    private Long workWearIssuedId;
    private LocalDate replacementDate;
    private LocalDate dateIssued;


}
