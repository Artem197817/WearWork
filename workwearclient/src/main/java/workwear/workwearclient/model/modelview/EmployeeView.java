package workwear.workwearclient.model.modelview;

import lombok.Data;
import workwear.workwearclient.model.Employee;

@Data
public class EmployeeView {

    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String productionDivision;
    private String company;
    private String specialization;

    public EmployeeView(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.patronymic = employee.getPatronymic();
        this.productionDivision = employee.getProductionDivision().getValue();
        this.company = employee.getCompany().getValue();
        this.specialization = employee.getSpecialization();
    }
}
