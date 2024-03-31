package workwear.workshoes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import workwear.workshoes.model.enumerated.WorkShoesType;

@Data
@AllArgsConstructor
public class WorkShoesOrder {

    private int workShoesSize;
    private WorkShoesType workShoesType;
}
