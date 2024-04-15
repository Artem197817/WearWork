package workwear.workshoes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import workwear.workshoes.model.WorkShoes;
import workwear.workshoes.model.WorkShoesIssued;
import workwear.workshoes.model.WorkShoesOrder;
import workwear.workshoes.model.WorkShoesTotal;
import workwear.workshoes.model.enumerated.WorkShoesType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkShoesOrderServiceImpl implements WorkShoesOrderService {

    private final WorkShoesService workShoesService;
    private final WorkShoesTotalService workShoesTotalService;
    private final WorkShoesIssuedService workShoesIssuedService;


    /**
     метод возвращает список WorkShoesTotal объектов для определенного типа рабочей обуви, которую необходимо заменить.
     Сначала он извлекает список рабочей обуви из WorkShoesIssuedService,
     а затем сортирует и фильтрует их на основе заданного workShoesType.
     */
    @Override
    public List<WorkShoesTotal> findWorShoesReplacedByType(WorkShoesType workShoesType) {
        List<WorkShoes> workShoesList = getWorkShoesFromShoesIssued().stream()
                .filter(ws -> ws.getWorkShoesType().equals(workShoesType))
                .toList();
        return workShoesTotalService.typeSortedNumber(workShoesList, workShoesType);
    }

    /**
     метод возвращает список WorkShoesTotal объектов для всех типов рабочей обуви, которые необходимо заменить.
     Сначала он извлекает список рабочей обуви из WorkShoesIssuedService, а затем сортирует и фильтрует их.
     */
    @Override
    public List<WorkShoesTotal> findWorkShoesAllReplaced() {
        return workShoesTotalService.sortedNumber(getWorkShoesFromShoesIssued()).stream()
                .sorted(Comparator.comparing(WorkShoesTotal::getWorkShoesType))
                .toList();
    }

    /**
     метод возвращает список WorkShoesOrder объектов для определенного типа рабочей обуви, у которых отсутствуют размеры.
     Сначала создается список всех возможных комбинаций размеров и типов,
     а затем отфильтровываются те, которые существуют в базе данных.
     */
    @Override
    public List<WorkShoesOrder> searchForMissingDimensionsByType(WorkShoesType workShoesType) {
        boolean is = true;
        List<WorkShoes> workShoesListAll = workShoesService.findAllWorkShoesByWorkShoesType(workShoesType);
        List<WorkShoesOrder> workShoesOrderListAll = new ArrayList<>();
        List<WorkShoesOrder> workShoesOrderList = new ArrayList<>();
        for (int i = WorkShoes.MIN_SIZE; i <= WorkShoes.MAX_SIZE; i++) {
            workShoesOrderListAll.add(new WorkShoesOrder(i, workShoesType));
        }
        for (WorkShoesOrder wso : workShoesOrderListAll) {
            int workShoesSize = wso.getWorkShoesSize();
            for (WorkShoes ws : workShoesListAll) {
                if (workShoesSize == ws.getWorkShoesSize()){
                    is = false;
                    break;
                }
            }
            if (is) workShoesOrderList.add(wso);
            is = true;
        }
        return workShoesOrderList;
    }

    /**
     метод возвращает список WorkShoesOrder объектов для всех типов рабочей обуви, у которых отсутствуют размеры.
     Сначала создается список всех возможных комбинаций размеров и типов для всей рабочей обуви,
     а затем отфильтровываются те, которые существуют в базе данных.
     */
    @Override
    public List<WorkShoesOrder> searchForMissingDimensionsAll() {
        boolean is = true;
        List<WorkShoes> workShoesListAll = workShoesService.findAllWorkShoes();
        List<WorkShoesOrder> workShoesOrderListAll = new ArrayList<>();
        List<WorkShoesOrder> workShoesOrderList = new ArrayList<>();
        for (WorkShoesType workShoesType : WorkShoesType.values()) {
            if (workShoesType.equals(WorkShoesType.OTHER)) continue;
            for (int i = WorkShoes.MIN_SIZE; i <= WorkShoes.MAX_SIZE; i++)  {
                workShoesOrderListAll.add(new WorkShoesOrder(i, workShoesType));
            }
        }
        for (WorkShoesOrder wso : workShoesOrderListAll) {
            WorkShoesType workShoesType = wso.getWorkShoesType();
            int workShoesSize = wso.getWorkShoesSize();
            for (WorkShoes ws : workShoesListAll) {
                if (workShoesType.equals(ws.getWorkShoesType()) && workShoesSize == ws.getWorkShoesSize()){
                    is = false;
                    break;
                }
            }
            if (is) workShoesOrderList.add(wso);
            is = true;
        }
        return workShoesOrderList;
    }

    /**
     метод, который извлекает список рабочей обуви из WorkShoesIssuedService, которую необходимо заменить.
     Он сопоставляет идентификаторы выпущенной рабочей обуви с реальными WorkShoes объектами, используя WorkShoesService.
     */
    private List<WorkShoes> getWorkShoesFromShoesIssued() {
        List<WorkShoesIssued> workShoesIssuedList = workShoesIssuedService.findWorkShoesToBeReplaced();
        return workShoesIssuedList.stream()
                .map(w -> workShoesService.findById(w.getWorkShoesId()))
                .toList();
    }
}
