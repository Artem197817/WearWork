package workwear.workwearclient.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.WorkWearTotalApiClient;
import workwear.workwearclient.model.WorkWearTotal;
import workwear.workwearclient.model.modelEnum.WorkWearSize;
import workwear.workwearclient.model.modelEnum.WorkWearType;


import java.util.List;


@Controller
@AllArgsConstructor
public class WorkWearTotalController {

    private final WorkWearTotalApiClient workWearTotalApiClient;

    public List<WorkWearTotal> findWorkWearByTypeSortedNumber(WorkWearType workWearType){
        return workWearTotalApiClient.findWorkWearByTypeSortedNumber(workWearType);
    }

    public List<WorkWearTotal> findWorkWearBySizeSortedNumber(WorkWearSize workWearSize){
        return workWearTotalApiClient.findWorkWearBySizeSortedNumber(workWearSize);
    }

    public List<WorkWearTotal> findAllWorkWearSortedNumber() {
      return workWearTotalApiClient.findAllWorkWearSortedNumber();
    }
}
