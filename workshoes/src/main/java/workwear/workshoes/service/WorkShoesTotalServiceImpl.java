package workwear.workshoes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workwear.workshoes.model.WorkShoes;
import workwear.workshoes.model.WorkShoesTotal;
import workwear.workshoes.model.enumerated.WorkShoesType;
import workwear.workshoes.repository.WorkShoesRepository;

import java.util.*;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class WorkShoesTotalServiceImpl implements WorkShoesTotalService {

    private final WorkShoesRepository workShoesRepository;

    /**
     метод находит рабочую обувь определенного типа, сортирует их по количеству и возвращает список WorkShoesTotal.
     Использует методы sortedWorkShoesNotIssued и typeSortedNumber.
     */
    @Override
    public List<WorkShoesTotal> findWorkShoesByTypeSortedNumber(WorkShoesType workShoesType) {
        List<WorkShoes> workShoesList = sortedWorkShoesNotIssued(workShoesRepository.findAllWorkShoesByWorkShoesType(workShoesType));
        return typeSortedNumber(workShoesList, workShoesType);
    }

    /**
     метод сортирует рабочую обувь по размеру и подсчитывает количество каждого размера, создавая объекты WorkShoesTotal.
     Возвращает список WorkShoesTotal.
     */
    @Override
    public List<WorkShoesTotal> typeSortedNumber(List<WorkShoes> workShoesList, WorkShoesType workShoesType) {
        if (workShoesList.isEmpty()) return new ArrayList<>();
        int number = 0;
        List<WorkShoesTotal> workShoesTotals = new ArrayList<>();
        List<WorkShoes> workShoesFilterSize = workShoesList.stream()
                .sorted(Comparator.comparing(WorkShoes::getWorkShoesSize))
                .toList();
        int workShoesSize = workShoesFilterSize.get(0).getWorkShoesSize();
        for (WorkShoes workShoes : workShoesFilterSize) {
            if (workShoesSize == workShoes.getWorkShoesSize()) {
                number++;
            } else {
                workShoesTotals.add(new WorkShoesTotal(workShoesType, workShoesSize, number));
                workShoesSize = workShoes.getWorkShoesSize();
                number = 1;
            }
        }
        workShoesTotals.add(new WorkShoesTotal(workShoesType, workShoesSize, number));
        return workShoesTotals;
    }

    /**
     Этот метод находит рабочую обувь определенного размера, сортирует их по количеству и возвращает список WorkShoesTotal.
     Использует методы sortedWorkShoesNotIssued и typeSortedNumber.
     */
    @Override
    public List<WorkShoesTotal> findWorkShoesBySizeSortedNumber(Integer workShoesSize) {
        int number = 0;
        List<WorkShoes> workShoesList = sortedWorkShoesNotIssued(workShoesRepository.findAllWorkShoesByWorkShoesSize(workShoesSize));
        List<WorkShoesTotal> workShoesTotals = new ArrayList<>();
        List<WorkShoes> workShoesFilterType = workShoesList.stream()
                .sorted(Comparator.comparing(WorkShoes::getWorkShoesType))
                .toList();
        try {
            WorkShoesType workShoesType = workShoesFilterType.get(0).getWorkShoesType();
            for (WorkShoes workShoes : workShoesFilterType) {
                if (workShoesType.equals(workShoes.getWorkShoesType())) {
                    number++;
                } else {
                    workShoesTotals.add(new WorkShoesTotal(workShoesType, workShoesSize, number));
                    workShoesType = workShoes.getWorkShoesType();
                    number = 1;
                }
            }
            workShoesTotals.add(new WorkShoesTotal(workShoesType, workShoesSize, number));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workShoesTotals;
    }


    /**
     Данный метод находит всю рабочую обувь, сортирует их по количеству и возвращает список WorkShoesTotal.
     Использует методы sortedWorkShoesNotIssued и sortedNumber.
     */
    @Override
    public List<WorkShoesTotal> findAllWorkShoesSortedNumber() {
        List<WorkShoes> workShoesList = sortedWorkShoesNotIssued(workShoesRepository.findAll());
        return sortedNumber(workShoesList);
    }

    @Override
    public List<WorkShoesTotal> sortedNumber(List<WorkShoes> workShoesList) {
        if (workShoesList.isEmpty()) return new ArrayList<>();
        List<WorkShoesTotal> workShoesTotals = new ArrayList<>();
        Set<WorkShoesType> workShoesTypeSet = new HashSet<>();
        for (WorkShoes workShoes : workShoesList)
            workShoesTypeSet.add(workShoes.getWorkShoesType());
        for (WorkShoesType workShoesType : workShoesTypeSet)
            workShoesTotals.addAll(findWorkShoesByTypeSortedNumber(workShoesType));
        return workShoesTotals;
    }

    private List<WorkShoes> sortedWorkShoesNotIssued(List<WorkShoes> workShoesList) {
        return workShoesList.stream()
                .filter(workShoes -> workShoes.getWorkShoesStatus() == WorkShoes.NOT_ISSUE)
                .toList();
    }
}
