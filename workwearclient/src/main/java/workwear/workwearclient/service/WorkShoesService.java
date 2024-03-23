package workwear.workwearclient.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import workwear.workwearclient.model.WorkShoes;
import workwear.workwearclient.model.modelEnum.WorkShoesType;
import workwear.workwearclient.model.modelview.WorkShoesArrival;
import workwear.workwearclient.model.modelview.WorkShoesView;
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

    public List<WorkShoes> createWorkShoes (WorkShoesArrival workShoesArrival){
        List<WorkShoes> workShoesList = new ArrayList<>();
        WorkShoesType workShoesType = WorkShoesType.getType(workShoesArrival.getWorkShoesType());
        for (int i = 0; i < workShoesArrival.getQuantity() ; i++) {
            workShoesList.add(new WorkShoes(workShoesArrival.getModelWorkShoes(),workShoesArrival.getWorkShoesSize(),
                    workShoesType));
        }
        return workShoesList;
    }

    public List<WorkShoesView> createShoesView(List<WorkShoes> workShoesList){
        return workShoesList.stream()
                .map(WorkShoesView::new)
                .toList();
    }
}
