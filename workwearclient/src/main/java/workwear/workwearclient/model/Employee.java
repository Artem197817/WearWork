package workwear.workwearclient.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import workwear.workwearclient.model.modelEnum.Company;
import workwear.workwearclient.model.modelEnum.ProductionDivision;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Long id;

    private String firstName;

    private String lastName;

    private String patronymic;
    private ProductionDivision productionDivision;
    private Company company;

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
