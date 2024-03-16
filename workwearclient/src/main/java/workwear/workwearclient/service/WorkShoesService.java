package workwear.workwearclient.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import workwear.workwearclient.model.WorkShoes;
import workwear.workwearclient.model.modelEnum.WorkShoesType;
import workwear.workwearclient.view.input.InputValue;

import java.util.*;

@Service
@AllArgsConstructor
public class WorkShoesService {

    private final InputValue inputValue;


    public List<WorkShoes> sortedWorkShoesNotIssue(List<WorkShoes> workShoesList){
       return workShoesList.stream()
                .filter(x -> x.getWorkShoesStatus() == WorkShoes.NOT_ISSUE)
                .sorted(Comparator.comparing(WorkShoes::getWorkShoesType))
                .toList();
    }

    public List<WorkShoes> createNewWorkShoes() {
       List<WorkShoes> workShoesList = new ArrayList<>();
        String modelWorkShoes = inputValue.input("Модель");
        String workShoesSize = inputValue.input("Размер");
        WorkShoesType  workShoesType = WorkShoesType.getType(inputValue.inputEnum("Тип", WorkShoesType.class));
        int number = inputValue.inputInt("Количество");
        for (int i = 0; i < number; i++) workShoesList.add(new WorkShoes(modelWorkShoes,workShoesSize,workShoesType));
        return workShoesList;
    }

}
