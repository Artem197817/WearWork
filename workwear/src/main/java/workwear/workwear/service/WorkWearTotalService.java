package workwear.workwear.service;

import workwear.workwear.model.WorkWearTotal;
import workwear.workwear.model.enumerated.WorkWearSize;
import workwear.workwear.model.enumerated.WorkWearType;

import java.util.List;

public interface WorkWearTotalService {

    List<WorkWearTotal> findWorkWearByTypeSortedNumber(WorkWearType workWearType);

    List<WorkWearTotal> findWorkWearBySizeSortedNumber(WorkWearSize workWearSize);

    List<WorkWearTotal> findAllWorkWearSortedNumber();

}