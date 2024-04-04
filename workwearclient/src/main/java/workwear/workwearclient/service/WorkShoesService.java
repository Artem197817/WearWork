package workwear.workwearclient.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import workwear.workwearclient.model.WorkShoes;
import workwear.workwearclient.model.modelEnum.WorkShoesType;
import workwear.workwearclient.model.modelview.WorkShoesArrival;
import workwear.workwearclient.model.modelview.WorkShoesView;


import java.util.*;

@Service
@AllArgsConstructor
public class WorkShoesService {



    public List<WorkShoes> sortedWorkShoesNotIssue(List<WorkShoes> workShoesList){
       return workShoesList.stream()
                .filter(x -> x.getWorkShoesStatus() == WorkShoes.NOT_ISSUE)
                .sorted(Comparator.comparing(WorkShoes::getWorkShoesType))
                .toList();
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

    public List<Integer> createSizeList(){
        List<Integer> workShoesSizeList = new ArrayList<>();
        for (int i = WorkShoes.MIN_SIZE; i <= WorkShoes.MAX_SIZE ; i++) {
            workShoesSizeList.add(i);
        }
        return workShoesSizeList;
    }
}
