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
    public static final int MIN_SIZE = 34;
    public static final int MAX_SIZE = 48;

    @Id
    @GeneratedValue
    private Long id;
    private String modelWorkShoes;

    @Min(MIN_SIZE)
    @Max(MAX_SIZE)
    private int workShoesSize;
    private WorkShoesType workShoesType;

    private int workShoesStatus = NOT_ISSUE;

}