package workwear.workwearclient.model.modelview;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WorkShoesIssuedView {

    private String modelWorkShoes;
    private int workShoesSize;
    private String workShoesType;
    private Long workShoesIssuedId;
    private LocalDate replacementDate;
    private LocalDate dateIssued;

}
