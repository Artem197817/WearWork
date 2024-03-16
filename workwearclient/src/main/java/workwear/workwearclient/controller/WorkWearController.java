package workwear.workwearclient.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.WorkWearApiClient;
import workwear.workwearclient.model.WorkWear;
import workwear.workwearclient.model.modelEnum.WorkWearType;
import workwear.workwearclient.service.WorkWearService;
import workwear.workwearclient.view.input.InputValue;

import java.util.List;


@Controller
@AllArgsConstructor
public class WorkWearController {

    private final WorkWearApiClient workWearApiClient;
    private final WorkWearService workWearService;
    private final InputValue inputValue;


    public List<WorkWear> findAllWorkWear() {
        return workWearApiClient.findAllWorkWear();
    }

    public void saveAllNewWorkWear() {
        List<WorkWear> workWearList = workWearService.createNewWorkWear();
        workWearApiClient.saveAllWorkWear(workWearList);
    }

    public void deleteWorkWear() {
       workWearApiClient.deleteWorkWear( inputValue.inputLong("id"));
    }

    public WorkWear findById() {
       return workWearApiClient.findByID( inputValue.inputLong("id"));
    }

    public List<WorkWear> findAllWorkWearByModelWorkWear() {
      return workWearApiClient.findAllWorkWearByModelWorkWear(inputValue.input("Модель"));
    }

    public List<WorkWear> findAllWorkWearByWorkWearType() {
        WorkWearType workWearType = WorkWearType.getType(inputValue.inputEnum("Тип спецодежды",WorkWearType.class));
     return workWearApiClient.findAllWorkWearByWorkWearType(workWearType);
    }
}
