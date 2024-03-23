package workwear.workwearclient.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import workwear.workwearclient.clientApi.WorkShoesIssuedApiClient;
import workwear.workwearclient.model.WorkShoesIssued;
import workwear.workwearclient.model.WorkWearIssued;
import workwear.workwearclient.model.modelview.WorkShoesIssuedView;
import workwear.workwearclient.service.WorkShoesIssuedService;
import workwear.workwearclient.view.input.InputValue;

import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
public class WorkShoesIssuedController {

    private WorkShoesIssuedService workShoesIssuedService;
    private final InputValue inputValue;
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

    public void deleteWorkShoesIssued() {
        workShoesIssuedApiClient.deleteWorkShoesIssued(inputValue.inputLong("id удаляемой записи"));
    }

    public void deleteWorkShoesIssued(Long id) {
        workShoesIssuedApiClient.deleteWorkShoesIssued(id);
    }

    public List<WorkShoesIssued> findWorkShoesIssuedByEmployee() {
        Long employeeId = employeeController.findEmployeeId();
        if (employeeId == -1L) return new ArrayList<>();
        return workShoesIssuedApiClient.findWorkShoesIssuedByEmployeeID(employeeId);
    }

    public List<WorkShoesIssuedView> findWorkShoesIssuedEmployee(Long id) {
        List<WorkShoesIssuedView> workShoesIssuedViewList = workShoesIssuedApiClient.findWorkShoesIssuedEmployee(id);
        if (workShoesIssuedViewList.isEmpty()) return new ArrayList<>();
        return workShoesIssuedViewList;
    }

    public WorkShoesIssued findById(Long id) {
        return workShoesIssuedApiClient.findById(id);
    }
}