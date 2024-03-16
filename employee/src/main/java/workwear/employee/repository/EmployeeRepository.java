package workwear.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workwear.employee.model.Employee;
import workwear.employee.model.enumerated.ProductionDivision;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllEmployeeByProductionDivision(ProductionDivision productionDivision);

    List<Employee> findAllEmployeeByLastName(String lastName);
}


