package workwear.workwearclient.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.EmployeeApiClient;
import workwear.workwearclient.model.Employee;
import workwear.workwearclient.model.modelEnum.ProductionDivision;
import workwear.workwearclient.service.EmployeeService;
import workwear.workwearclient.view.input.InputValue;
import workwear.workwearclient.view.output.Output;

import java.util.Comparator;
import java.util.List;


@Controller
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeApiClient employeeApiClient;
    private final InputValue inputValue;
    private final EmployeeService employeeService;
    private final Output output;


    public List<Employee> findAllEmployee() {
        return employeeApiClient.findAllEmployee().stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .toList();
    }

    public void saveNewEmployee() {
        employeeApiClient.saveEmployee(employeeService.createNewEmployee());
    }

    public void saveEmployee(Employee employee) {
        employeeApiClient.saveEmployee(employee);
    }

    public void deleteEmployee() {
        employeeApiClient.deleteEmployee(inputValue.inputLong("id"));
    }

    public void deleteEmployeeById(Long id) {
        employeeApiClient.deleteEmployee(id);
    }

    public Employee findById() {
        return employeeApiClient.findById(inputValue.inputLong("id сотрудника"));
    }

    public Employee findById(Long id) {
        return employeeApiClient.findById(id);
    }

    public Employee updateEmployee() {
        return null;
    }

    public List<Employee> findAllEmployeeByProductionDivision() {
        ProductionDivision productionDivision = ProductionDivision.getType(inputValue.inputEnum("Участок", ProductionDivision.class));
        return employeeApiClient.findAllEmployeeByProductionDivision(productionDivision);
    }

    public List<Employee> findEmployeeByLastName() {
        return employeeApiClient.findAllEmployeeByLastName(inputValue.input("Фамилия сотрудника"));
    }

    public Employee findEmployee() {
        List<Employee> employeeList = findEmployeeByLastName();
        output.outputList(employeeList);
        Long idEmployee = inputValue.inputLong("Введите id сотрудника для выдачи спецодежды ");
        return employeeList.stream().filter(employee -> employee.getId().equals(idEmployee)).findFirst().orElse(null);
    }

    public Long findEmployeeId() {
        List<Employee> employees = findEmployeeByLastName();
        if (employees.isEmpty()) {
            output.output("Нет сотрудника с такой фамилией");
            return -1L;
        }
        output.outputList(employees);
        output.output("Введите id сотрудника");
        return findById().getId();

    }
}
