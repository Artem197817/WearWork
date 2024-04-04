package workwear.workwear.service;

import workwear.workwear.model.WorkWear;
import workwear.workwear.model.WorkWearTotal;
import workwear.workwear.model.enumerated.WorkWearSize;
import workwear.workwear.model.enumerated.WorkWearType;

import java.util.List;

public interface WorkWearTotalService {

    List<WorkWearTotal> findWorkWearByTypeSortedNumber(WorkWearType workWearType);

    List<WorkWearTotal> findWorkWearBySizeSortedNumber(WorkWearSize workWearSize);

    List<WorkWearTotal> findAllWorkWearSortedNumber();
    List<WorkWearTotal> sortedNumber(List<WorkWear> workWearList);

    List<WorkWearTotal> typeSortedNumber(List<WorkWear> workWearList, WorkWearType workWearType);
    List<WorkWearTotal> sizeSortedNumber(List<WorkWear> workWearList, WorkWearSize workWearSize);
}