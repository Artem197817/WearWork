package workwear.workwearclient.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
public class WorkWearIssued {


    private Long id;
    private Long employeeId;
    private Long workWearId;

    private Integer monthPeriod;
    private LocalDate replacementDate;
    private LocalDate dateIssued;


    public WorkWearIssued(Long employeeId,Long workWearId,Integer monthPeriod){
        this.employeeId = employeeId;
        this.workWearId = workWearId;
        this.replacementDate = LocalDate.now().plusMonths(monthPeriod);
        this.dateIssued = LocalDate.now();
    }

    @Override
    public String toString() {
        return "WorkWearIssued{" +
                "id - " + id +
                ", id сотрудника - " + employeeId +
                ", id выданной сотруднику спецодежды - " + workWearId +
                ", Дата замены - " + replacementDate +
                ", Дата выдачи - " + dateIssued +
                '}';
    }
}
