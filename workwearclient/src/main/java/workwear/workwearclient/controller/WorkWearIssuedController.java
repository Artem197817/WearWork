package workwear.workwearclient.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.WorkWearIssuedApiClient;
import workwear.workwearclient.model.WorkWear;
import workwear.workwearclient.model.WorkWearIssued;
import workwear.workwearclient.service.WorkWearIssueService;
import workwear.workwearclient.view.input.InputValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class WorkWearIssuedController {

    private final WorkWearIssuedApiClient workWearIssuedApiClient;
    private WorkWearIssueService workWearIssuedService;
    private final InputValue inputValue;
    private final EmployeeController employeeController;



    public void saveWorkWearIssued() {
        WorkWearIssued workWearIssued = workWearIssuedService.issuedWorkWear();
        if (workWearIssued == null) return;
        workWearIssuedApiClient.saveWorkWearIssued(workWearIssued);
    }

    public List<WorkWearIssued> findAllWorkWearIssued() {
     return workWearIssuedApiClient.findAllWorkWearIssued();
    }

    public void deleteWorkWearIssued() {
        workWearIssuedApiClient.deleteWorkWearIssued( inputValue.inputLong("id удаляемой записи"));
    }

    public List<WorkWearIssued> findWorkWearIssuedByEmployee() {
        Long employeeId = employeeController.findEmployeeId();
        if (employeeId == -1L) return new ArrayList<>();
      return workWearIssuedApiClient.findWorkWearIssuedByEmployeeId(employeeId);
    }
    public Map<WorkWearIssued,WorkWear> findWorkWearIssuedEmployee(Long id){
        return workWearIssuedApiClient.findWorkWearIssuedEmployee(id);
    }
}

