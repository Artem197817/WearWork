package workwear.workwear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workwear.workwear.model.WorkWear;
import workwear.workwear.model.enumerated.WorkWearSize;
import workwear.workwear.model.enumerated.WorkWearType;

import java.util.List;

public interface WorkWearRepository extends JpaRepository<WorkWear, Long> {

    List<WorkWear> findAllWorkWearByModelWorkWear(String modelWorkWear);

    List<WorkWear> findAllWorkWearByWorkWearType(WorkWearType workWearType);

    List<WorkWear> findAllWorkWearByWorkWearSize(WorkWearSize workWearSize);

}
