package workwear.workwear.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import workwear.workwear.model.enumerated.WorkWearHeight;
import workwear.workwear.model.enumerated.WorkWearSize;
import workwear.workwear.model.enumerated.WorkWearType;

@Data
@Entity
@Table(name = "workWear")
@NoArgsConstructor
public class WorkWear {

    public static final int ISSUE = -1;
    public static final int NOT_ISSUE = 1;

    @Id
    @GeneratedValue
    private Long id;
    private String modelWorkWear;
    private WorkWearType workWearType;
    private WorkWearSize workWearSize;
    private WorkWearHeight workWearHeight;
    private int WorkWearStatus = NOT_ISSUE;


}
