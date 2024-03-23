package workwear.workwearclient.model.modelview;

import lombok.Data;
import workwear.workwearclient.model.WorkShoesTotal;

@Data
public class WorkShoesTotalView {

    private String workShoesType;
    private Integer workShoesSize;
    private Integer number;

    public WorkShoesTotalView(WorkShoesTotal workShoesTotal) {
        this.workShoesType = workShoesTotal.getWorkShoesType().getValue();
        this.workShoesSize = workShoesTotal.getWorkShoesSize();
        this.number = workShoesTotal.getNumber();
    }
}
