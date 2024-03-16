package workwear.workwearclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import workwear.workwearclient.model.modelEnum.WorkShoesType;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkShoesTotal {

    private WorkShoesType workShoesType;
    private Integer workShoesSize;
    private Integer number;

}
