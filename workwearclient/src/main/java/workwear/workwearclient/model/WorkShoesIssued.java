package workwear.workwearclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WorkShoesIssued {


    private Long id;
    private Long employeeId;
    private Long workShoesId;

    private Integer monthPeriod;
    private LocalDate replacementDate;
    private LocalDate dateIssued;

    public WorkShoesIssued(Long employeeId, Long workShoesId, Integer monthPeriod) {
        this.employeeId = employeeId;
        this.workShoesId = workShoesId;
        this.monthPeriod = monthPeriod;
        this.dateIssued = LocalDate.now();
        this.replacementDate = dateIssued.plusMonths(monthPeriod);
    }

    @Override
    public String toString() {
        return "id=" + id + "." +
                ", employeeId=" + employeeId +
                ", workShoesId=" + workShoesId +
                ", monthPeriod=" + monthPeriod +
                ", replacementDate=" + replacementDate +
                ", dateIssued=" + dateIssued +
                '}';
    }
}
