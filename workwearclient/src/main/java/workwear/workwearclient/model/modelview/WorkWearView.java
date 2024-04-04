package workwear.workwearclient.model.modelview;

import lombok.Data;
import lombok.NoArgsConstructor;
import workwear.workwearclient.model.WorkWear;


@Data
@NoArgsConstructor
public class WorkWearView {

    private Long id;
    private String modelWorkWear;
    private String workWearType;
    private String workWearSize;
    private String workWearHeight;

    public WorkWearView(WorkWear workWear) {
        this.id = workWear.getId();
        this.modelWorkWear = workWear.getModelWorkWear();
        this.workWearType = workWear.getWorkWearType().getValue();
        this.workWearSize = workWear.getWorkWearSize().getValue();
        this.workWearHeight = workWear.getWorkWearHeight().getValue();
    }
}
