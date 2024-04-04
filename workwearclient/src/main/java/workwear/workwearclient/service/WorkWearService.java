package workwear.workwearclient.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import workwear.workwearclient.model.WorkWear;
import workwear.workwearclient.model.modelEnum.WorkWearType;
import workwear.workwearclient.model.modelview.WorkWearArrival;
import workwear.workwearclient.model.modelview.WorkWearView;


import java.util.*;

@Service
@AllArgsConstructor
public class WorkWearService {


    public List<WorkWear> sortedWorkWearNotIssue(List<WorkWear> workWearList) {
        return workWearList.stream()
                .filter(x -> x.getWorkWearStatus() == WorkWear.NOT_ISSUE)
                .sorted(Comparator.comparing(WorkWear::getWorkWearType))
                .toList();
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

    public List<WorkWearView> createWorkWearView(List<WorkWear> workWearList){
        return workWearList.stream()
                .sorted(Comparator.comparing(w -> w.getWorkWearSize().getValue()))
                .map(WorkWearView::new)
                .toList();
    }

}

    
    

