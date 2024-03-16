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

    @Override
    public List<WorkShoesTotal> findWorkShoesByTypeSortedNumber(WorkShoesType workShoesType) {
        int number = 0;
        List<WorkShoes> workShoesList = sortedWorkShoesNotIssued(workShoesRepository.findAllWorkShoesByWorkShoesType(workShoesType));
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

    @Override
    public List<WorkShoesTotal> findAllWorkShoesSortedNumber() {
        List<WorkShoes> workShoesList = sortedWorkShoesNotIssued(workShoesRepository.findAll());
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
