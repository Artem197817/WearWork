package workwear.workwearclient.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.WorkShoesTotalApiClient;
import workwear.workwearclient.model.WorkShoesTotal;
import workwear.workwearclient.model.modelEnum.WorkShoesType;
import workwear.workwearclient.view.input.InputValue;

import java.util.List;

@Controller
@AllArgsConstructor
public class WorkShoesTotalController {

    private final InputValue inputValue;
    private final WorkShoesTotalApiClient workShoesTotalApiClient;


    public List<WorkShoesTotal> findWorkShoesByTypeSortedNumber() {
        WorkShoesType workShoesType = WorkShoesType.getType(inputValue.inputEnum("Тип", WorkShoesType.class));
        return workShoesTotalApiClient.findWorkShoesByTypeSortedNumber(workShoesType);
    }

    public List<WorkShoesTotal> findWorkShoesByTypeSortedNumber(WorkShoesType workShoesType){
        return workShoesTotalApiClient.findWorkShoesByTypeSortedNumber(workShoesType);
    }

    public List<WorkShoesTotal> findWorkShoesBySizeSortedNumber() {
        return workShoesTotalApiClient.findWorkShoesBySizeSortedNumber(inputValue.inputInt("Размер"));
    }

    public List<WorkShoesTotal> findWorkShoesBySizeSortedNumber(Integer size){
        return workShoesTotalApiClient.findWorkShoesBySizeSortedNumber(size);
    }

    public List<WorkShoesTotal> findAllWorkShoesSortedNumber() {
        return workShoesTotalApiClient.findAllWorkShoesSortedNumber();
    }
}
