package workwear.employee.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import workwear.employee.model.Employee;
import workwear.employee.model.enumerated.ProductionDivision;
import workwear.employee.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Data
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping
    public List<Employee> findAllEmployee() {
        return employeeService.findAllEmployee();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping("save_employee")
    public String saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return "Employee successfully saved";
    }

    @PutMapping("update_employee")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/delete_employee/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/pd/{productionDivision}")
    public List<Employee> findAllEmployeeByProductionDivision(@PathVariable ProductionDivision productionDivision) {
        return employeeService.findAllEmployeeByProductionDivision(productionDivision);
    }

    @GetMapping("/last_name/{lastName}")
    public List<Employee> findAllEmployeeByLastName(@PathVariable String lastName) {
        return employeeService.findAllEmployeeByLastName(lastName);
    }

}