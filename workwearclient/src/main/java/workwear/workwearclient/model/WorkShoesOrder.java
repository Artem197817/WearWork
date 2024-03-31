package workwear.workwearclient.model;

import lombok.Data;
import workwear.workwearclient.model.modelEnum.WorkShoesType;

@Data
public class WorkShoesOrder {

    private int workShoesSize;
    private WorkShoesType workShoesType;
}
