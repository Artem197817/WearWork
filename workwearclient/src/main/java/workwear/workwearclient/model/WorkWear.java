package workwear.workwearclient.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import workwear.workwearclient.model.modelEnum.WorkWearHeight;
import workwear.workwearclient.model.modelEnum.WorkWearSize;
import workwear.workwearclient.model.modelEnum.WorkWearType;

@Data
@NoArgsConstructor
public class WorkWear {

    public static final int ISSUE = -1;
    public static final int NOT_ISSUE = 1;

    private Long id;
    private String modelWorkWear;
    private WorkWearType workWearType;
    private WorkWearSize workWearSize;
    private WorkWearHeight workWearHeight;
    private int WorkWearStatus = NOT_ISSUE;

    public WorkWear(String modelWorkWear, WorkWearType workWearType, WorkWearSize workWearSize, WorkWearHeight workWearHeight) {
        this.modelWorkWear = modelWorkWear;
        this.workWearType = workWearType;
        this.workWearSize = workWearSize;
        this.workWearHeight = workWearHeight;
    }
}
