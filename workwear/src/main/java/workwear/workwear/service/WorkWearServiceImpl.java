package workwear.workwear.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workwear.workwear.model.WorkWear;
import workwear.workwear.model.enumerated.WorkWearSize;
import workwear.workwear.model.enumerated.WorkWearType;
import workwear.workwear.repository.WorkWearRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class WorkWearServiceImpl implements WorkWearService{

    private final WorkWearRepository workWearRepository;

    @Override
    public List<WorkWear> findAllWorkWear() {
        return workWearRepository.findAll();
    }

    @Override
    @Transactional
    public void saveWorkWear(WorkWear workWear) {
        workWearRepository.save(workWear);
    }
    @Override
    @Transactional
    public List<WorkWear> saveAllWorkWear(List<WorkWear> workWearList){
        return workWearRepository.saveAll(workWearList).stream()
                .sorted(Comparator.comparing(WorkWear::getWorkWearType))
                .sorted(Comparator.comparing(WorkWear::getWorkWearSize))
                .toList();
    }
    @Override
    public WorkWear findById(Long id) {
        Optional<WorkWear> workWear = workWearRepository.findById(id);
        return workWear.orElse(null);
    }

    @Override
    @Transactional
    public WorkWear updateWorkWear(WorkWear workWear) {
        return workWearRepository.save(workWear);
    }

    @Override
    @Transactional
    public void deleteWorkWearById(Long id) {
        workWearRepository.deleteById(id);
    }

    @Override
    public List<WorkWear> findAllWorkWearByModelWorkWear(String modelWorkWear) {
        return workWearRepository.findAllWorkWearByModelWorkWear(modelWorkWear).stream()
                .sorted(Comparator.comparing(WorkWear::getWorkWearType))
                .toList();
    }

    @Override
    public List<WorkWear> findAllWorkWearByWorkWearType(WorkWearType workWearType) {
        return workWearRepository.findAllWorkWearByWorkWearType(workWearType).stream()
                .sorted(Comparator.comparing(WorkWear::getWorkWearSize))
                .toList();
    }

    @Override
    public List<WorkWear> findAllWorkWearByWorkWearSize(WorkWearSize workWearSize) {
        return workWearRepository.findAllWorkWearByWorkWearSize(workWearSize).stream()
                .sorted(Comparator.comparing(WorkWear::getWorkWearType))
                .toList();
    }
}
