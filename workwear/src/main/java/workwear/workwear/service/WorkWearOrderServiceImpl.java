package workwear.workwear.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import workwear.workwear.model.WorkWear;
import workwear.workwear.model.WorkWearIssued;
import workwear.workwear.model.WorkWearOrder;
import workwear.workwear.model.WorkWearTotal;
import workwear.workwear.model.enumerated.WorkWearHeight;
import workwear.workwear.model.enumerated.WorkWearSize;
import workwear.workwear.model.enumerated.WorkWearType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkWearOrderServiceImpl implements WorkWearOrderService{

    private final WorkWearIssuedService workWearIssuedService;
    private final WorkWearTotalService workWearTotalService;
    private final WorkWearService workWearService;

    /**
     * Метод для поиска всей рабочей одежды подлежащей замене
     */
    @Override
    public List<WorkWearTotal> findWorkWearAllReplaced(){
        return  workWearTotalService.sortedNumber(getWorkWearFromWearIssued()).stream()
                .sorted(Comparator.comparing (WorkWearTotal::getWorkWearType))
                .toList();
    }

    /**
     * Получение списка выданной рабочей одежды, подлежащей замене
     */
    private List<WorkWear> getWorkWearFromWearIssued(){
        List<WorkWearIssued> workWearIssuedList = workWearIssuedService.findWorkWearIssuedToBeReplaced();
        return workWearIssuedList.stream()
                .map(w-> workWearService.findById(w.getWorkWearId()))
                .toList();
    }

    /**
     * Метод для поиска рабочей одежды определенного типа, подлежащей замене
     * @param workWearType тип рабочей одежды
     * @return список замененных рабочих одежд данного типа
     */
    @Override
    public List<WorkWearTotal> findWorkWearReplacedByType(WorkWearType workWearType){
        List<WorkWear> workWearList = getWorkWearFromWearIssued().stream()
                .filter(ww->ww.getWorkWearType().equals(workWearType))
                .toList();
        return workWearTotalService.typeSortedNumber(workWearList,workWearType);
    }

    /**
     * Метод для поиска всех отсутствующих размеров рабочей одежды
     * @return список заказов на отсутствующие размеры рабочей одежды
     */
    @Override
    public List<WorkWearOrder> searchForMissingDimensionsAll() {
        boolean is = true;
        List<WorkWear> workWearListAll = workWearService.findAllWorkWear();
        List<WorkWearOrder> workWearOrderListAll = new ArrayList<>();
        List<WorkWearOrder> workWearOrderList = new ArrayList<>();
        for (WorkWearType workWearType : WorkWearType.values()) {
            if (workWearType.equals(WorkWearType.OTHER)) continue;
            for (WorkWearSize workWearSize : WorkWearSize.values()) {
                if (workWearSize.equals(WorkWearSize.UNKNOWN)) continue;
                for (WorkWearHeight workWearHeight : WorkWearHeight.values()) {
                    if (workWearHeight.equals(WorkWearHeight.UNKNOWN)) continue;
                    workWearOrderListAll.add(new WorkWearOrder(workWearType, workWearSize, workWearHeight));
                }
            }
        }
        for (WorkWearOrder wwo : workWearOrderListAll) {
            WorkWearType workWearType = wwo.getWorkWearType();
            WorkWearSize workWearSize = wwo.getWorkWearSize();
            WorkWearHeight workWearHeight = wwo.getWorkWearHeight();
            for (WorkWear ww : workWearListAll) {
                if (workWearType.equals(ww.getWorkWearType()) && workWearSize.equals(ww.getWorkWearSize())
                        && workWearHeight.equals(ww.getWorkWearHeight())) {
                    is = false;
                    break;
                }
            }
            if (is)  workWearOrderList.add(wwo);
            is = true;
        }
        return  workWearOrderList;
    }

    /**
     * Метод для поиска отсутствующих размеров рабочей одежды определенного типа
     * @param workWearType тип рабочей одежды
     * @return список заказов на отсутствующие размеры рабочей одежды данного типа
     */
    @Override
    public  List<WorkWearOrder> searchForMissingDimensionsByType(WorkWearType workWearType){
        boolean is = true;
        List<WorkWear> workWearListAll = workWearService.findAllWorkWearByWorkWearType(workWearType);
        List<WorkWearOrder> workWearOrderListAll = new ArrayList<>();
        List<WorkWearOrder> workWearOrderList = new ArrayList<>();
        for (WorkWearSize workWearSize : WorkWearSize.values()) {
            if (workWearSize.equals(WorkWearSize.UNKNOWN)) continue;
            for (WorkWearHeight workWearHeight : WorkWearHeight.values()) {
                if (workWearHeight.equals(WorkWearHeight.UNKNOWN)) continue;
                workWearOrderListAll.add(new WorkWearOrder(workWearType, workWearSize, workWearHeight));
            }
        }
        for (WorkWearOrder wwo : workWearOrderListAll) {
            WorkWearSize workWearSize = wwo.getWorkWearSize();
            WorkWearHeight workWearHeight = wwo.getWorkWearHeight();
            for (WorkWear ww : workWearListAll) {
                if (workWearSize.equals(ww.getWorkWearSize())
                        && workWearHeight.equals(ww.getWorkWearHeight())) {
                    is = false;
                    break;
                }
            }
            if (is)  workWearOrderList.add(wwo);
            is = true;
        }
        return  workWearOrderList;
    }
}
