package workwear.workwearclient.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import workwear.workwearclient.model.modelEnum.WorkWearHeight;
import workwear.workwearclient.model.modelEnum.WorkWearSize;
import workwear.workwearclient.model.modelEnum.WorkWearType;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkWearTotal {

    private WorkWearType workWearType;
    private WorkWearSize workWearSize;
    private WorkWearHeight workWearHeight;
    private int number;

}
