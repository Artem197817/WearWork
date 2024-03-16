package workwear.workwearclient.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.WorkWearTotalApiClient;
import workwear.workwearclient.model.WorkWearTotal;
import workwear.workwearclient.model.modelEnum.WorkWearSize;
import workwear.workwearclient.model.modelEnum.WorkWearType;
import workwear.workwearclient.view.input.InputValue;

import java.util.List;

@Controller
@AllArgsConstructor
public class WorkWearTotalController {

    private final WorkWearTotalApiClient workWearTotalApiClient;
    private final InputValue inputValue;

    public List<WorkWearTotal> findWorkWearByTypeSortedNumber() {
        WorkWearType workWearType = WorkWearType.getType(inputValue.inputEnum("Тип",WorkWearType.class));
        return workWearTotalApiClient.findWorkWearByTypeSortedNumber(workWearType);
    }

    public List<WorkWearTotal> findWorkWearBySizeSortedNumber() {
        WorkWearSize workWearSize = WorkWearSize.getType(inputValue.inputEnum("Размер",WorkWearSize.class));
        return workWearTotalApiClient.findWorkWearBySizeSortedNumber(workWearSize);
    }

    public List<WorkWearTotal> findAllWorkWearSortedNumber() {
      return workWearTotalApiClient.findAllWorkWearSortedNumber();
    }
}
