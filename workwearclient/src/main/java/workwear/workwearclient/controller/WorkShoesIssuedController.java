package workwear.workwearclient.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.WorkShoesIssuedApiClient;
import workwear.workwearclient.model.WorkShoes;
import workwear.workwearclient.model.WorkShoesIssued;
import workwear.workwearclient.service.WorkShoesIssuedService;
import workwear.workwearclient.view.input.InputValue;
import workwear.workwearclient.view.output.Output;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class WorkShoesIssuedController {

    private WorkShoesIssuedService workShoesIssuedService;
    private final InputValue inputValue;
    private final Output output;
    private final EmployeeController employeeController;
    private final WorkShoesIssuedApiClient workShoesIssuedApiClient;


    public List<WorkShoesIssued> findAllWorkShoesIssued() {
        return workShoesIssuedApiClient.findAllWorkShoesIssued();
    }

    public void saveWorkShoesIssued() {
        WorkShoesIssued workShoesIssued = workShoesIssuedService.issuedWorkShoes();
        if (workShoesIssued == null) return;
        workShoesIssuedApiClient.saveWorkShoesIssued(workShoesIssued);
    }

    public void deleteWorShoesIssued() {
        workShoesIssuedApiClient.deleteWorkShoesIssued(inputValue.inputLong("id удаляемой записи"));
    }

    public List<WorkShoesIssued> findWorkShoesIssuedByEmployee() {
        Long employeeId = employeeController.findEmployeeId();
        if (employeeId == -1L) return new ArrayList<>();
        return workShoesIssuedApiClient.findWorkShoesIssuedByEmployeeID(employeeId);
    }

    public Map<WorkShoesIssued,WorkShoes> findWorkShoesIssuedEmployee (Long id){
        return workShoesIssuedApiClient.findWorkShoesIssuedEmployee(id);
    }
}