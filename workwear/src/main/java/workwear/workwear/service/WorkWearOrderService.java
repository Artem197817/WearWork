package workwear.workwear.service;

import workwear.workwear.model.WorkWearOrder;
import workwear.workwear.model.WorkWearTotal;
import workwear.workwear.model.enumerated.WorkWearType;

import java.util.List;

public interface WorkWearOrderService {

    List<WorkWearTotal> findWorkWearReplacedByType(WorkWearType workWearType);

    List<WorkWearTotal>  findWorkWearAllReplaced();

    List<WorkWearOrder> searchForMissingDimensionsByType(WorkWearType workWearType);

    List<WorkWearOrder> searchForMissingDimensionsAll();
}
