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


    @Override
    public List<WorkShoesTotal> findWorShoesReplacedByType(WorkShoesType workShoesType) {
        List<WorkShoes> workShoesList = getWorkShoesFromShoesIssued().stream()
                .filter(ws -> ws.getWorkShoesType().equals(workShoesType))
                .toList();
        return workShoesTotalService.typeSortedNumber(workShoesList, workShoesType);
    }

    @Override
    public List<WorkShoesTotal> findWorkShoesAllReplaced() {
        return workShoesTotalService.sortedNumber(getWorkShoesFromShoesIssued()).stream()
                .sorted(Comparator.comparing(WorkShoesTotal::getWorkShoesType))
                .toList();
    }

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

    private List<WorkShoes> getWorkShoesFromShoesIssued() {
        List<WorkShoesIssued> workShoesIssuedList = workShoesIssuedService.findWorkShoesToBeReplaced();
        return workShoesIssuedList.stream()
                .map(w -> workShoesService.findById(w.getWorkShoesId()))
                .toList();
    }
}
