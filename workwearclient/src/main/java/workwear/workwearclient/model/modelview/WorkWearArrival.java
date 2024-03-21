package workwear.workwearclient.model.modelview;

import lombok.Data;
import workwear.workwearclient.model.modelEnum.WorkWearHeight;
import workwear.workwearclient.model.modelEnum.WorkWearSize;
import workwear.workwearclient.model.modelEnum.WorkWearType;

@Data
public class WorkWearArrival {

    private String modelWorkWear;
    private String workWearType;
    private WorkWearSize workWearSize;
    private WorkWearHeight workWearHeight;
    private int quantity;
}
