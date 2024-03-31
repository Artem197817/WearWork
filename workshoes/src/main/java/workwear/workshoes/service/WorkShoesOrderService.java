package workwear.workshoes.service;


import workwear.workshoes.model.WorkShoesOrder;
import workwear.workshoes.model.WorkShoesTotal;
import workwear.workshoes.model.enumerated.WorkShoesType;

import java.util.List;

public interface WorkShoesOrderService {

    List<WorkShoesTotal> findWorShoesReplacedByType(WorkShoesType workShoesType);

    List<WorkShoesTotal>  findWorkShoesAllReplaced();

    List<WorkShoesOrder> searchForMissingDimensionsByType(WorkShoesType workShoesType);

    List<WorkShoesOrder> searchForMissingDimensionsAll();
}
