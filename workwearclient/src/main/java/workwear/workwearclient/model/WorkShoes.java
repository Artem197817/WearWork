package workwear.workwearclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import workwear.workwearclient.model.modelEnum.WorkShoesType;


@Data
@NoArgsConstructor
public class WorkShoes {

    public static final int ISSUE = -1;
    public static final int NOT_ISSUE = 1;

    private Long id;
    private String modelWorkShoes;

    private int workShoesSize;
    private WorkShoesType workShoesType;

    private int workShoesStatus = NOT_ISSUE;

    public WorkShoes(String modelWorkShoes, String workShoesSize, WorkShoesType workShoesType) {
        this.modelWorkShoes = modelWorkShoes;
        this.workShoesSize = Integer.parseInt(workShoesSize);
        this.workShoesType = workShoesType;

    }
}
