package workwear.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import workwear.employee.model.enumerated.Company;
import workwear.employee.model.enumerated.ProductionDivision;


@Data
@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String patronymic;
    private ProductionDivision productionDivision;
    private Company company;
    @NotBlank
    private String specialization;

    public Employee(String firstName, String lastName, String patronymic, ProductionDivision productionDivision, Company company, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.productionDivision = productionDivision;
        this.company = company;
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "id=" + id + "." +
                " " + lastName + '\'' +
                " " + firstName + '\'' +
                " " + patronymic + '\'' +
                " участок " + productionDivision.getValue() +
                " компания " + company.getValue() +
                " специальность'" + specialization + '\'';
    }
}
