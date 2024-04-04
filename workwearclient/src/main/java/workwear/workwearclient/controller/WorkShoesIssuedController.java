package workwear.workwearclient.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.WorkShoesIssuedApiClient;
import workwear.workwearclient.model.WorkShoesIssued;
import workwear.workwearclient.model.modelview.WorkShoesIssuedView;

import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
public class WorkShoesIssuedController {

    private final WorkShoesIssuedApiClient workShoesIssuedApiClient;


    public void saveWorkShoesIssued(WorkShoesIssued workShoesIssued){
        workShoesIssuedApiClient.saveWorkShoesIssued(workShoesIssued);
    }

    public void deleteWorkShoesIssued(Long id) {
        workShoesIssuedApiClient.deleteWorkShoesIssued(id);
    }

    public List<WorkShoesIssuedView> findWorkShoesIssuedEmployee(Long id) {
        List<WorkShoesIssuedView> workShoesIssuedViewList = workShoesIssuedApiClient.findWorkShoesIssuedEmployee(id);
        if (workShoesIssuedViewList.isEmpty()) return new ArrayList<>();
        return workShoesIssuedViewList;
    }

    public WorkShoesIssued findById(Long id) {
        return workShoesIssuedApiClient.findById(id);
    }


    public void returnWorkShoesOnStorage(Long id){
        workShoesIssuedApiClient.returnWorkShoesOnStorage(id);
    }
}