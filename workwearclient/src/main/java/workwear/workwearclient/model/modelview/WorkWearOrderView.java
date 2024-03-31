package workwear.workwearclient.model.modelview;

import lombok.Data;
import workwear.workwearclient.model.WorkWearOrder;

@Data
public class WorkWearOrderView {

    private String workWearType;
    private String workWearSize;
    private String workWearHeight;

    public WorkWearOrderView(WorkWearOrder workWearOrder) {
        this.workWearType = workWearOrder.getWorkWearType().getValue();
        this.workWearSize = workWearOrder.getWorkWearSize().getValue();
        this.workWearHeight = workWearOrder.getWorkWearHeight().getValue();
    }
}
