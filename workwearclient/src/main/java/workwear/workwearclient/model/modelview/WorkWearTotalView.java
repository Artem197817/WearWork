package workwear.workwearclient.model.modelview;

import lombok.Data;
import workwear.workwearclient.model.WorkWearTotal;

@Data
public class WorkWearTotalView {

    private String workWearType;
    private String workWearSize;
    private String workWearHeight;
    private int number;

    public WorkWearTotalView(WorkWearTotal workWearTotal) {
        this.workWearType = workWearTotal.getWorkWearType().getValue();
        this.workWearSize = workWearTotal.getWorkWearSize().getValue();
        this.workWearHeight = workWearTotal.getWorkWearHeight().getValue();
        this.number = workWearTotal.getNumber();
    }
}
