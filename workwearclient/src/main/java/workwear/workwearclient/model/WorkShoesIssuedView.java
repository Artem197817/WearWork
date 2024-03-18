package workwear.workwearclient.model;

import lombok.Data;
import workwear.workwearclient.model.modelEnum.WorkShoesType;

import java.time.LocalDate;

@Data
public class WorkShoesIssuedView {

    private Long workShoesId;
    private String modelWorkShoes;
    private int workShoesSize;
    private String workShoesType;
    private Long workShoesIssuedId;
    private LocalDate replacementDate;
    private LocalDate dateIssued;

    public WorkShoesIssuedView (WorkShoesIssued workShoesIssued,WorkShoes workShoes){
        this.workShoesId = workShoes.getId();
        this.modelWorkShoes = workShoes.getModelWorkShoes();
        this.workShoesSize = workShoes.getWorkShoesSize();
        this.workShoesType = workShoes.getWorkShoesType().getValue();
        this.workShoesIssuedId = workShoesIssued.getId();
        this.dateIssued = workShoesIssued.getDateIssued();
        this.replacementDate = workShoesIssued.getReplacementDate();
    }
}
