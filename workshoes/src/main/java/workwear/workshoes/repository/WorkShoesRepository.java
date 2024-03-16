package workwear.workshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workwear.workshoes.model.WorkShoes;
import workwear.workshoes.model.enumerated.WorkShoesType;

import java.util.List;

public interface WorkShoesRepository extends JpaRepository<WorkShoes,Long> {

    List<WorkShoes> findAllWorkShoesByWorkShoesSize (Integer workShoesSize);

    List<WorkShoes> findAllWorkShoesByWorkShoesType (WorkShoesType workShoesType);
}
