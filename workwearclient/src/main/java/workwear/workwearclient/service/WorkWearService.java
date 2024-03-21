package workwear.workwearclient.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import workwear.workwearclient.model.WorkWear;
import workwear.workwearclient.model.modelEnum.WorkWearHeight;
import workwear.workwearclient.model.modelEnum.WorkWearSize;
import workwear.workwearclient.model.modelEnum.WorkWearType;
import workwear.workwearclient.model.modelview.WorkWearArrival;
import workwear.workwearclient.view.input.InputValue;

import java.util.*;

@Service
@AllArgsConstructor
public class WorkWearService {

    private final InputValue inputValue;


    public List<WorkWear> sortedWorkWearNotIssue(List<WorkWear> workWearList) {
        return workWearList.stream()
                .filter(x -> x.getWorkWearStatus() == WorkWear.NOT_ISSUE)
                .sorted(Comparator.comparing(WorkWear::getWorkWearType))
                .toList();
    }

    public List<WorkWear> createNewWorkWear() {
        List<WorkWear> workWearList = new ArrayList<>();
        String modelWorkWear = inputValue.input("Модель");
        WorkWearType workWearType = WorkWearType.getType(inputValue.inputEnum("Тип", WorkWearType.class));
       WorkWearSize workWearSize = WorkWearSize.getType( inputValue.inputEnum("Размер", WorkWearSize.class));
         WorkWearHeight wworkWearHeight = WorkWearHeight.getType(inputValue.inputEnum("Рост", WorkWearHeight.class));
        int number = inputValue.inputInt("Количество");
        for (int i = 0; i < number; i++) workWearList.add(new WorkWear(modelWorkWear,workWearType,workWearSize,wworkWearHeight));

        return workWearList;
    }
    public List<WorkWear> createWorkWearList(WorkWearArrival workWearArrival){
        List<WorkWear> workWearList = new ArrayList<>();
        WorkWearType workWearType = WorkWearType.getType(workWearArrival.getWorkWearType());
        for (int i=0;i<workWearArrival.getQuantity();i++){
            workWearList.add(new WorkWear(workWearArrival.getModelWorkWear(),workWearType,
                    workWearArrival.getWorkWearSize(),workWearArrival.getWorkWearHeight()));
        }
        return workWearList;
    }
}

    
    

