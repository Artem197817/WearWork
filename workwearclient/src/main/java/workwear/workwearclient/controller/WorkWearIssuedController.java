package workwear.workwearclient.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.WorkWearIssuedApiClient;
import workwear.workwearclient.model.WorkWearIssued;
import workwear.workwearclient.model.modelview.WorkWearIssuedView;
import workwear.workwearclient.service.WorkWearIssueService;
import workwear.workwearclient.view.input.InputValue;

import java.util.ArrayList;
import java.util.List;


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

    public void saveWorkWearIssued(WorkWearIssued workWearIssued){
        workWearIssuedApiClient.saveWorkWearIssued(workWearIssued);
    }

    public List<WorkWearIssued> findAllWorkWearIssued() {
        return workWearIssuedApiClient.findAllWorkWearIssued();
    }

    public void deleteWorkWearIssued() {
        workWearIssuedApiClient.deleteWorkWearIssued(inputValue.inputLong("id удаляемой записи"));
    }

    public void deleteWorkWearIssued(Long id) {
        workWearIssuedApiClient.deleteWorkWearIssued(id);
    }

    public List<WorkWearIssued> findWorkWearIssuedByEmployee() {
        Long employeeId = employeeController.findEmployeeId();
        if (employeeId == -1L) return new ArrayList<>();
        return workWearIssuedApiClient.findWorkWearIssuedByEmployeeId(employeeId);
    }

    public List<WorkWearIssuedView> findWorkWearIssuedEmployee(Long id) {
        List<WorkWearIssuedView> workWearIssuedViewList = workWearIssuedApiClient.findWorkWearIssuedEmployee(id);
        if (workWearIssuedViewList.isEmpty()) return new ArrayList<>();
        return workWearIssuedViewList;
    }

    public WorkWearIssued findWorkWearIssuedById(Long id) {
        return workWearIssuedApiClient.findWorkWearIssuedById(id);
    }


    public void returnWorkWearOnStorage(Long id){
        workWearIssuedApiClient.returnWorkWearOnStorage(id);
    }
}

