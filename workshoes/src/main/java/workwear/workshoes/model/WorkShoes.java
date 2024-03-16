package workwear.workshoes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import workwear.workshoes.model.enumerated.WorkShoesType;

@Entity
@Data
@Table(name = "workShoes")
@NoArgsConstructor
public class WorkShoes {

    public static final int ISSUE = -1;
    public static final int NOT_ISSUE = 1;

    @Id
    @GeneratedValue
    private Long id;
    private String modelWorkShoes;

    @Min(34)
    @Max(48)
    private int workShoesSize;
    private WorkShoesType workShoesType;

    private int workShoesStatus = NOT_ISSUE;

    public WorkShoes(String modelWorkShoes, String workShoesSize, WorkShoesType workShoesType) {
        this.modelWorkShoes = modelWorkShoes;
        this.workShoesSize = Integer.parseInt(workShoesSize);
        this.workShoesType = workShoesType;

    }
}