package workwear.workwearclient.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import workwear.workwearclient.model.Employee;
import workwear.workwearclient.model.modelEnum.Company;
import workwear.workwearclient.model.modelEnum.ProductionDivision;
import workwear.workwearclient.view.input.InputValue;


@Service
@AllArgsConstructor
public class EmployeeService {

    private final InputValue inputValue;


        public Employee createNewEmployee(){
            String firstName = inputValue.input("Имя");
            String lastName = inputValue.input("Фамилия");
            String patronymic = inputValue.input("Отчество");
            ProductionDivision productionDivision = ProductionDivision.getType( inputValue.inputEnum("Участок", ProductionDivision.class));
            Company company = Company.getType( inputValue.inputEnum("Компания", Company.class));
            String specialization = inputValue.input("Профессия");
            return new Employee(firstName,lastName,patronymic,productionDivision,company,specialization);
        }

    }

