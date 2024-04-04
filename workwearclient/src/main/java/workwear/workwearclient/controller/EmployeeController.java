package workwear.workwearclient.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.EmployeeApiClient;
import workwear.workwearclient.model.Employee;
import workwear.workwearclient.model.modelEnum.ProductionDivision;



import java.util.Comparator;
import java.util.List;


@Controller
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeApiClient employeeApiClient;



    public List<Employee> findAllEmployee() {
        return employeeApiClient.findAllEmployee().stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .toList();
    }

    public void saveEmployee(Employee employee) {
        employeeApiClient.saveEmployee(employee);
    }

    public void deleteEmployeeById(Long id) {
        employeeApiClient.deleteEmployee(id);
    }

    public Employee findById(Long id) {
        return employeeApiClient.findById(id);
    }

    public List<Employee> findAllEmployeeByProductionDivision(ProductionDivision productionDivision) {
        return employeeApiClient.findAllEmployeeByProductionDivision(productionDivision);
    }

    public List<Employee> findEmployeeByLastName(String lastName){
        return  employeeApiClient.findAllEmployeeByLastName(lastName);
    }

}
