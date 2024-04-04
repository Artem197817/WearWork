package workwear.workwearclient.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.WorkWearIssuedApiClient;
import workwear.workwearclient.model.WorkWearIssued;
import workwear.workwearclient.model.modelview.WorkWearIssuedView;


import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
public class WorkWearIssuedController {

    private final WorkWearIssuedApiClient workWearIssuedApiClient;


    public void saveWorkWearIssued(WorkWearIssued workWearIssued){
        workWearIssuedApiClient.saveWorkWearIssued(workWearIssued);
    }

    public void deleteWorkWearIssued(Long id) {
        workWearIssuedApiClient.deleteWorkWearIssued(id);
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

