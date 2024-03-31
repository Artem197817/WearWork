package workwear.workwear.service;

import workwear.workwear.model.WorkWear;
import workwear.workwear.model.WorkWearOrder;
import workwear.workwear.model.enumerated.WorkWearSize;
import workwear.workwear.model.enumerated.WorkWearType;

import java.util.List;

public interface WorkWearService {

    List<WorkWear> findAllWorkWear();

    void saveWorkWear(WorkWear workWear);

    WorkWear findById(Long id);

    WorkWear updateWorkWear(WorkWear workWear);

    void deleteWorkWearById(Long id);

    public List<WorkWear> saveAllWorkWear(List<WorkWear> workWearList);

    List<WorkWear> findAllWorkWearByModelWorkWear(String modelWorkWear);

    List<WorkWear> findAllWorkWearByWorkWearType(WorkWearType workWearType);

    List<WorkWear> findAllWorkWearByWorkWearSize(WorkWearSize workWearSize);

}