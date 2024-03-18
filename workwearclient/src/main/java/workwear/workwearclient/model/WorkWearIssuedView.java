package workwear.workwearclient.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkWearIssuedView {


    private Long workWearId;
    private String modelWorkWear;
    private String workWearType;
    private String workWearSize;
    private String workWearHeight;
    private Long workWearIssuedId;
    private LocalDate replacementDate;
    private LocalDate dateIssued;


    private WorkWearIssuedView(WorkWearIssued workWearIssued,WorkWear workWear){
        this.workWearId = workWear.getId();
        this.modelWorkWear=workWear.getModelWorkWear();
        this.workWearType = workWear.getWorkWearType().getValue();
        this.workWearSize = workWear.getWorkWearSize().getValue();
        this.workWearHeight = workWear.getWorkWearHeight().getValue();
        this.workWearIssuedId = workWearIssued.getId();
        this.dateIssued = workWearIssued.getDateIssued();
        this.replacementDate = workWearIssued.getReplacementDate();
    }
}
