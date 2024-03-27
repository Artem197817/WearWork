package workwear.workshoes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workwear.workshoes.model.WorkShoes;
import workwear.workshoes.model.enumerated.WorkShoesType;
import workwear.workshoes.repository.WorkShoesRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class WorkShoesServiceImpl implements WorkShoesService{

    private final WorkShoesRepository workShoesRepository;

    @Override
    public List<WorkShoes> findAllWorkShoes() {
        return workShoesRepository.findAll().stream()
                .sorted(Comparator.comparing(WorkShoes::getWorkShoesType))
                .toList();
    }

    @Override
    @Transactional
    public void saveWorkShoes(WorkShoes workShoes) {
        workShoesRepository.save(workShoes);
    }

    @Override
    public WorkShoes findById(Long id) {
        Optional<WorkShoes> workShoes = workShoesRepository.findById(id);
        return workShoes.orElse(null);
    }

    @Override
    @Transactional
    public WorkShoes updateWorkShoes(WorkShoes workShoes) {
        return workShoesRepository.save(workShoes);
    }

    @Override
    @Transactional
    public void deleteWorkShoes(Long id) {
        workShoesRepository.deleteById(id);
    }

    @Override
    public List<WorkShoes> findAllWorkShoesByWorkShoesSize(Integer workShoesSize) {
        return workShoesRepository.findAllWorkShoesByWorkShoesSize(workShoesSize).stream()
                .sorted(Comparator.comparing(WorkShoes::getWorkShoesType))
                .toList();
    }

    @Override
    public List<WorkShoes> findAllWorkShoesByWorkShoesType(WorkShoesType workShoesType) {
        return workShoesRepository.findAllWorkShoesByWorkShoesType(workShoesType).stream()
                .sorted(Comparator.comparing(WorkShoes::getWorkShoesSize))
                .toList();
    }

    @Override
    @Transactional
    public void saveAllWorkShoes(List<WorkShoes> workShoesList) {
        workShoesRepository.saveAll(workShoesList);
    }
}