package workwear.workwearclient.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import workwear.workwearclient.model.Employee;
import workwear.workwearclient.model.modelview.EmployeeView;


import java.util.List;


@Service
@AllArgsConstructor
public class EmployeeService {

    public List<EmployeeView> createEmployeeView(List<Employee>  employeeList){
        return employeeList.stream()
                .map(EmployeeView::new)
                .toList();
    }

    }

