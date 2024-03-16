package workwear.employee.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workwear.employee.model.Employee;
import workwear.employee.model.enumerated.ProductionDivision;
import workwear.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllEmployeeByProductionDivision(ProductionDivision productionDivision) {
        return employeeRepository.findAllEmployeeByProductionDivision(productionDivision);
    }

    @Override
    public List<Employee> findAllEmployeeByLastName(String lastName) {
        return employeeRepository.findAllEmployeeByLastName(lastName);
    }
}

