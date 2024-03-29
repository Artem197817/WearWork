package workwear.workwearclient.model.modelview;

import lombok.Data;
import lombok.NoArgsConstructor;
import workwear.workwearclient.model.WorkShoes;


@Data
@NoArgsConstructor
public class WorkShoesView {

    private Long id;
    private String modelWorkShoes;
    private int workShoesSize;
    private String workShoesType;

    public WorkShoesView(WorkShoes workShoes) {
        this.id = workShoes.getId();
        this.modelWorkShoes =workShoes.getModelWorkShoes();
        this.workShoesSize = workShoes.getWorkShoesSize();
        this.workShoesType = workShoes.getWorkShoesType().getValue();
    }
}
