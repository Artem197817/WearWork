package workwear.workwearclient.model.modelview;

import lombok.Data;
import workwear.workwearclient.model.modelEnum.WorkShoesType;

@Data
public class WorkShoesArrival {

    private String modelWorkShoes;
    private int workShoesSize;
    private String workShoesType;
    private int quantity;
}
