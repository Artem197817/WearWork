package workwear.workshoes.service;

import org.springframework.web.bind.annotation.RequestBody;
import workwear.workshoes.model.WorkShoes;
import workwear.workshoes.model.enumerated.WorkShoesType;

import java.util.List;

public interface WorkShoesService {

    List<WorkShoes> findAllWorkShoes();

    void saveWorkShoes(WorkShoes workShoes);

    WorkShoes findById(Long id);

    WorkShoes updateWorkShoes(WorkShoes workShoes);

    void deleteWorkShoes(Long id);

    List<WorkShoes> findAllWorkShoesByWorkShoesSize (Integer workShoesSize);

    List<WorkShoes> findAllWorkShoesByWorkShoesType (WorkShoesType workShoesType);

    void saveAllWorkShoes (@RequestBody List<WorkShoes> workShoesList);
}