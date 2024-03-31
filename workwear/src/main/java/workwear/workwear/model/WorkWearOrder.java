package workwear.workwear.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import workwear.workwear.model.enumerated.WorkWearHeight;
import workwear.workwear.model.enumerated.WorkWearSize;
import workwear.workwear.model.enumerated.WorkWearType;

@Data
@AllArgsConstructor
public class WorkWearOrder {

    private WorkWearType workWearType;
    private WorkWearSize workWearSize;
    private WorkWearHeight workWearHeight;
}
