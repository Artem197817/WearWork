package workwear.workwearclient.clientApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import workwear.workwearclient.model.Employee;
import workwear.workwearclient.model.modelEnum.ProductionDivision;

import java.util.List;


@FeignClient(name = "employee", url = "http://localhost:8089/employee")
public interface EmployeeApiClient {

    @GetMapping
    List<Employee> findAllEmployee();

    @GetMapping("/{id}")
    Employee findById(@PathVariable Long id);

    @PostMapping("/save_employee")
    String saveEmployee(@RequestBody Employee employee);

    @PutMapping("/update_employee")
    Employee updateEmployee(@RequestBody Employee employee);

    @DeleteMapping("/delete_employee/{id}")
    void deleteEmployee(@PathVariable long id);

    @GetMapping("/pd/{productionDivision}")
    List<Employee> findAllEmployeeByProductionDivision(@PathVariable ProductionDivision productionDivision);

    @GetMapping("/lastname/{lastName}")
    List<Employee> findAllEmployeeByLastName(@PathVariable String lastName);

}
