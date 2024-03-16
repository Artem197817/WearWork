package workwear.workshoes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workwear.workshoes.model.WorkShoes;
import workwear.workshoes.model.WorkShoesIssued;
import workwear.workshoes.repository.WorkShoesIssuedRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class WorkShoesIssuedServiceImpl implements WorkShoesIssuedService {

    private final WorkShoesIssuedRepository workShoesIssuedRepository;
    private final WorkShoesService workShoesService;

    @Override
    public List<WorkShoesIssued> findAllWorkShoesIssued() {
        return workShoesIssuedRepository.findAll();
    }

    @Override
    @Transactional
    public String saveWorkShoesIssued(WorkShoesIssued workShoesIssued) {
        WorkShoes workShoes = workShoesService.findById((workShoesIssued.getWorkShoesId()));
        workShoes.setWorkShoesStatus(WorkShoes.ISSUE);
        workShoesService.updateWorkShoes(workShoes);
        workShoesIssuedRepository.save(workShoesIssued);
        return "WorkShoesIssued save";
    }

    @Override
    public WorkShoesIssued findById(Long id) {
        Optional<WorkShoesIssued> workShoesIssued = workShoesIssuedRepository.findById(id);
        return workShoesIssued.orElse(null);
    }

    @Override
    @Transactional
    public WorkShoesIssued updateWorkShoesIssued(WorkShoesIssued workShoesIssued) {
        return workShoesIssuedRepository.save(workShoesIssued);
    }

    @Override
    @Transactional
    public void deleteWorkShoesIssued(Long id) {
        workShoesService.deleteWorkShoes(findById(id).getWorkShoesId());
        workShoesIssuedRepository.deleteById(id);
    }

    @Override
    public List<WorkShoesIssued> findWorkShoesIssuedByEmployeeId(Long id) {
        return workShoesIssuedRepository.findWorkShoesIssuedByEmployeeId(id);
    }

    @Override
    public List<WorkShoesIssued> findWorkShoesToBeReplaced() {
        LocalDate localDateControl = LocalDate.now().plusMonths(1);
        return workShoesIssuedRepository.findAll().stream().
                filter(workWearIssued -> workWearIssued.getReplacementDate().isBefore(localDateControl))
                .toList();
    }
}