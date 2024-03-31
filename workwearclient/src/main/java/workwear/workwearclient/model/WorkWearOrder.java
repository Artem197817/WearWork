package workwear.workwearclient.model;

import lombok.Data;
import workwear.workwearclient.model.modelEnum.WorkWearHeight;
import workwear.workwearclient.model.modelEnum.WorkWearSize;
import workwear.workwearclient.model.modelEnum.WorkWearType;

@Data
public class WorkWearOrder {

    private WorkWearType workWearType;
    private WorkWearSize workWearSize;
    private WorkWearHeight workWearHeight;
}
