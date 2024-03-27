package workwear.workwear.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class WorkWearIssued {

    @Id
    @GeneratedValue
    private Long id;
    private Long employeeId;
    private Long workWearId;
    @Transient
    private Integer monthPeriod;
    private LocalDate replacementDate;
    private LocalDate dateIssued;



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
