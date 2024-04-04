package workwear.workwearclient.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import workwear.workwearclient.clientApi.WorkShoesApiClient;
import workwear.workwearclient.model.WorkShoes;
import workwear.workwearclient.model.modelEnum.WorkShoesType;
import workwear.workwearclient.model.modelview.WorkShoesArrival;
import workwear.workwearclient.service.WorkShoesService;


import java.util.List;


@Controller
@AllArgsConstructor
public class WorkShoesController {

    private final WorkShoesService workShoesService;
    private final WorkShoesApiClient workShoesApiClient;


    public List<WorkShoes> findAllWorkShoes() {
        return workShoesApiClient.findAllWorkShoes();
    }

    public void deleteWorkShoes(Long id) {
        workShoesApiClient.deleteWorkShoes(id);
    }

    public WorkShoes findById(Long id) {
        return workShoesApiClient.findById(id);
    }

    public List<WorkShoes> findAllWorkShoesByWorkShoesSize(Integer size) {
        return workShoesApiClient.findAllWorkShoesByWorkShoesSize(size);
    }

    public List<WorkShoes> findAllWorkShoesByWorkShoesType(WorkShoesType workShoesType){
        return workShoesApiClient.findAllWorkShoesByWorkShoesType(workShoesType);
    }

    public String saveWorkShoes(WorkShoesArrival workShoesArrival) {
        if (workShoesArrival.getQuantity() <= 0) {return "Заданы неверные данные";}
        workShoesApiClient.saveAllWorkShoes(workShoesService.createWorkShoes(workShoesArrival));
        return "Сохранено " + workShoesArrival.getWorkShoesType();
    }

    public void saveWorkShoes(WorkShoes workShoes){
        workShoesApiClient.saveWorkShoes(workShoes);
    }
}

