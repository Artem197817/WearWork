package workwear.workwearclient.model.modelview;

import lombok.Data;
import workwear.workwearclient.model.WorkShoesOrder;


@Data
public class WorkShoesOrderView {

    private int workShoesSize;
    private String  workShoesType;

    public WorkShoesOrderView(WorkShoesOrder workShoesOrder) {
        this.workShoesSize = workShoesOrder.getWorkShoesSize();
        this.workShoesType = workShoesOrder.getWorkShoesType().getValue();
    }
}
