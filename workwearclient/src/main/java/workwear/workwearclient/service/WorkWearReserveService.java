package workwear.workwearclient.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import workwear.workwearclient.model.WorkShoesTotal;
import workwear.workwearclient.model.WorkWearTotal;
import workwear.workwearclient.model.modelview.WorkShoesTotalView;
import workwear.workwearclient.model.modelview.WorkWearTotalView;

import java.util.List;

@Data
@Service
public class WorkWearReserveService {

    public List<WorkWearTotalView> createWorWearTotalView(List<WorkWearTotal> workWearTotalList){
        return workWearTotalList.stream()
                .map(WorkWearTotalView::new)
                .toList();
    }

    public List<WorkShoesTotalView> createWorkShoesTotalView (List<WorkShoesTotal> workShoesTotalList){
        return workShoesTotalList.stream()
                .map(WorkShoesTotalView::new)
                .toList();
    }
}

