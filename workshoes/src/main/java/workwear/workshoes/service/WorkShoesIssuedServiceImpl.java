package workwear.workshoes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import workwear.workshoes.model.WorkShoes;
import workwear.workshoes.model.WorkShoesIssued;
import workwear.workshoes.model.WorkShoesIssuedView;
import workwear.workshoes.repository.WorkShoesIssuedRepository;

import java.time.LocalDate;
import java.util.*;

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

    @Override
    public WorkShoesIssued findWorkShoesIssuedByWorkShoesId(Long id) {
        return workShoesIssuedRepository.findWorkShoesIssuedByWorkShoesId(id);
    }

    @Override
    public List<WorkShoesIssuedView> findWorkShoesIssuedEmployee(Long id) {
        List<WorkShoesIssued> workShoesIssuedList = findWorkShoesIssuedByEmployeeId(id);
        List<WorkShoesIssuedView> workShoesIssuedViewList = new ArrayList<>();
        if (workShoesIssuedList.isEmpty()) return workShoesIssuedViewList;
        for (WorkShoesIssued wsi : workShoesIssuedList) {
            workShoesIssuedViewList.add(new WorkShoesIssuedView(wsi, workShoesService.findById(wsi.getWorkShoesId())));
        }
        return workShoesIssuedViewList;
    }

    @Override
    @Transactional
    public String returnWorkShoesOnStorage(@PathVariable Long workShoesIssuedId) {
        WorkShoesIssued workShoesIssued = findById(workShoesIssuedId);
        WorkShoes workShoes = workShoesService.findById(workShoesIssued.getWorkShoesId());
        workShoes.setWorkShoesStatus(WorkShoes.NOT_ISSUE);
        workShoesService.updateWorkShoes(workShoes);
        workShoesIssuedRepository.deleteById(workShoesIssued.getId());
        return workShoes.getWorkShoesType().getValue() + " размер "
                + workShoes.getWorkShoesSize() + " возвращен на склад";
    }

}