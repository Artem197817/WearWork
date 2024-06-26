package workwear.workwear.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workwear.workwear.model.WorkWear;
import workwear.workwear.model.WorkWearTotal;
import workwear.workwear.model.enumerated.WorkWearHeight;
import workwear.workwear.model.enumerated.WorkWearSize;
import workwear.workwear.model.enumerated.WorkWearType;

import java.util.*;

@Data
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class WorkWearTotalServiceImpl implements WorkWearTotalService {

    private final WorkWearService workWearService;
    private final WorkWearIssuedService workWearIssuedService;

    /**
     * Метод для поиска рабочей одежды определенного типа, отсортированной по количеству
     * @param workWearType тип рабочей одежды
     * @return список объектов WorkWearTotal, отсортированный по количеству
     */
    @Override
    public List<WorkWearTotal> findWorkWearByTypeSortedNumber(WorkWearType workWearType){
        List<WorkWear> workWearList = sortedWorkWearNotIssued(workWearService.findAllWorkWearByWorkWearType(workWearType));
        return typeSortedNumber(workWearList,workWearType);
    }

    /**
     * Вспомогательный метод для сортировки рабочей одежды по количеству и размеру
     * @param workWearList список рабочей одежды
     * @param workWearType тип рабочей одежды
     * @return список объектов WorkWearTotal, отсортированный по количеству
     */
    public List<WorkWearTotal> typeSortedNumber(List<WorkWear> workWearList, WorkWearType workWearType) {
        int number = 0;
        List<WorkWearTotal> workWearTotalList = new ArrayList<>();
        Set<WorkWearSize> workWearSizeSet = new HashSet<>();
        List<WorkWear> workWearListFilterSize;
        for (WorkWear wear : workWearList)
            workWearSizeSet.add(wear.getWorkWearSize());
        for (WorkWearSize wearSize : workWearSizeSet) {
            workWearListFilterSize = workWearList.stream()
                    .filter(x -> wearSize.equals(x.getWorkWearSize()))
                    .sorted(Comparator.comparing(WorkWear::getWorkWearSize))
                    .sorted(Comparator.comparing(WorkWear::getWorkWearHeight))
                    .toList();
            WorkWearHeight workWearHeight = workWearListFilterSize.get(0).getWorkWearHeight();
            for (WorkWear workWear : workWearListFilterSize) {
                if (workWearHeight.equals(workWear.getWorkWearHeight())) {
                    number++;
                } else {
                    workWearTotalList.add(new WorkWearTotal(workWearType, wearSize, workWearHeight, number));
                    workWearHeight = workWear.getWorkWearHeight();
                    number = 1;
                }
            }
            workWearTotalList.add(new WorkWearTotal(workWearType, wearSize, workWearHeight, number));
            number = 0;
        }
        return workWearTotalList;
    }

    /**
     * Метод для поиска рабочей одежды определенного размера, отсортированной по количеству
     * @param workWearSize размер рабочей одежды
     * @return список объектов WorkWearTotal, отсортированный по количеству
     */
    @Override
    public List<WorkWearTotal> findWorkWearBySizeSortedNumber(WorkWearSize workWearSize){
        List<WorkWear> workWearList = sortedWorkWearNotIssued(workWearService.findAllWorkWearByWorkWearSize(workWearSize));
        return sizeSortedNumber(workWearList, workWearSize);
    }

    /**
     * Вспомогательный метод для сортировки рабочей одежды по количеству и типу
     * @param workWearList список рабочей одежды
     * @param workWearSize размер рабочей одежды
     * @return список объектов WorkWearTotal, отсортированный по количеству
     */
    public List<WorkWearTotal> sizeSortedNumber(List<WorkWear> workWearList, WorkWearSize workWearSize) {
        int number = 0;
        List<WorkWearTotal> workWearTotalList = new ArrayList<>();
        Set<WorkWearType> workWearTypeSet = new HashSet<>();
        List<WorkWear> workWearListFilterType;
        for (WorkWear workWear : workWearList) {
            workWearTypeSet.add(workWear.getWorkWearType());
        }
        for (WorkWearType wearType : workWearTypeSet) {
            workWearListFilterType = workWearList.stream()
                    .filter(x -> wearType.equals(x.getWorkWearType()))
                    .sorted(Comparator.comparing(WorkWear::getWorkWearHeight))
                    .toList();
            WorkWearHeight workWearHeight = workWearListFilterType.get(0).getWorkWearHeight();
            for (WorkWear workWear : workWearListFilterType) {
                if (workWearHeight.equals(workWear.getWorkWearHeight())) {
                    number++;
                } else {
                    workWearTotalList.add(new WorkWearTotal(wearType, workWearSize, workWearHeight, number));
                    workWearHeight = workWear.getWorkWearHeight();
                    number = 1;
                }
            }
            workWearTotalList.add(new WorkWearTotal(wearType, workWearSize, workWearHeight, number));
            number = 0;
        }
        return workWearTotalList;
    }

    /**
     * Метод для поиска всей рабочей одежды, отсортированной по количеству
     * @return список объектов WorkWearTotal, отсортированный по количеству
     */
    @Override
    public List<WorkWearTotal> findAllWorkWearSortedNumber() {
        List<WorkWear> workWearList = sortedWorkWearNotIssued(workWearService.findAllWorkWear());
        return sortedNumber(workWearList);
    }

    /**
     * Вспомогательный метод для сортировки рабочей одежды по количеству
     * @param workWearList список рабочей одежды
     * @return список объектов WorkWearTotal, отсортированный по количеству
     */
    public List<WorkWearTotal> sortedNumber(List<WorkWear> workWearList){
        List<WorkWearTotal> workWearTotalList = new ArrayList<>();
        Set<WorkWearType> workWearTypeSet = new HashSet<>();
        for (WorkWear workWear : workWearList) {
            workWearTypeSet.add(workWear.getWorkWearType());
        }
        for (WorkWearType wearType : workWearTypeSet) {
            workWearTotalList.addAll(findWorkWearByTypeSortedNumber(wearType));
        }
        return workWearTotalList;
    }
    /**
     * Вспомогательный метод для получения списка свободной рабочей одежды.
     * @param workWearList список рабочей одежды
     * @return список свободной рабочей одежды.
     */
    private List<WorkWear> sortedWorkWearNotIssued(List<WorkWear> workWearList) {
        return workWearList.stream()
                .filter(workWear -> workWear.getWorkWearStatus() == WorkWear.NOT_ISSUE)
                .toList();
    }

}
