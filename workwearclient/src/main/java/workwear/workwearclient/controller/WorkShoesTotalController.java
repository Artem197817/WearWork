package workwear.workwearclient.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.WorkShoesTotalApiClient;
import workwear.workwearclient.model.WorkShoesTotal;
import workwear.workwearclient.model.modelEnum.WorkShoesType;

import java.util.List;

@Controller
@AllArgsConstructor
public class WorkShoesTotalController {

    private final WorkShoesTotalApiClient workShoesTotalApiClient;


    public List<WorkShoesTotal> findWorkShoesByTypeSortedNumber(WorkShoesType workShoesType){
        return workShoesTotalApiClient.findWorkShoesByTypeSortedNumber(workShoesType);
    }

    public List<WorkShoesTotal> findWorkShoesBySizeSortedNumber(Integer size){
        return workShoesTotalApiClient.findWorkShoesBySizeSortedNumber(size);
    }

    public List<WorkShoesTotal> findAllWorkShoesSortedNumber() {
        return workShoesTotalApiClient.findAllWorkShoesSortedNumber();
    }
}
