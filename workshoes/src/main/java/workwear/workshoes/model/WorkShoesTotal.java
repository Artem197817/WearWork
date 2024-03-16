package workwear.workshoes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import workwear.workshoes.model.enumerated.WorkShoesType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkShoesTotal {

    private WorkShoesType workShoesType;
    private Integer workShoesSize;
    private Integer number;

}
