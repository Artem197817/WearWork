package workwear.workwear.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import workwear.workwear.model.WorkWear;
import workwear.workwear.model.WorkWearIssued;
import workwear.workwear.model.WorkWearIssuedView;
import workwear.workwear.repository.WorkWearIssuedRepository;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class WorkWearIssuedServiceImpl implements WorkWearIssuedService {

    private final WorkWearIssuedRepository workWearIssuedRepository;
    private final WorkWearService workWearService;

    @Override
    public List<WorkWearIssued> findAllWorkWearIssued() {
        return workWearIssuedRepository.findAll();
    }

    @Override
    public WorkWearIssued findWorkWearIssuedById(Long id) {
        Optional<WorkWearIssued> workWearIssued = workWearIssuedRepository.findById(id);
        return workWearIssued.orElse(null);
    }

    @Override
    @Transactional
    public String saveWorkWearIssued(WorkWearIssued workWearIssued) {
        WorkWear workWear = workWearService.findById(workWearIssued.getWorkWearId());
        workWear.setWorkWearStatus(WorkWear.ISSUE);
        workWearService.updateWorkWear(workWear);
        workWearIssuedRepository.save(workWearIssued);
        return "WorkWearIssued save";
    }

    @Override
    @Transactional
    public String updateWorkWearIssued(WorkWearIssued workWearIssued) {
        workWearIssuedRepository.save(workWearIssued);
        return "WorkWearIssued update";
    }

    @Override
    @Transactional
    public void deleteWorkWearIssuedById(Long id) {
        try {
            workWearService.deleteWorkWearById(findWorkWearIssuedById(id).getWorkWearId());
            workWearIssuedRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("Error id");
        }
    }

    @Override
    public List<WorkWearIssued> findWorkWearIssuedByEmployeeId(Long id) {
        return workWearIssuedRepository.findWorkWearIssuedByEmployeeId(id);
    }

    @Override
    public List<WorkWearIssued> findWorkWearIssuedToBeReplaced() {
        LocalDate localDateControl = LocalDate.now().plusMonths(1);
        return workWearIssuedRepository.findAll().stream().
                filter(workWearIssued -> workWearIssued.getReplacementDate().isBefore(localDateControl))
                .toList();
    }

    @Override
    public List<WorkWearIssuedView> findWorkWearIssuedEmployee(Long id) {
        List<WorkWearIssued> workWearIssuedList = findWorkWearIssuedByEmployeeId(id);
        if (workWearIssuedList.isEmpty()) return new ArrayList<>();
        List<WorkWearIssuedView> workWearIssuedViewList = new ArrayList<>();
        for (WorkWearIssued wwi: workWearIssuedList){
            workWearIssuedViewList.add(new WorkWearIssuedView(wwi,workWearService.findById(wwi.getWorkWearId())));
        }
        return workWearIssuedViewList;
    }

    @Override
    public WorkWearIssued findWorkWearIssuedByWorkWearId(Long id) {
        return workWearIssuedRepository.findWorkWearIssuedByWorkWearId(id);
    }

    @Override
    @Transactional
    public String returnWorkWearOnStorage(@PathVariable Long id){
        WorkWearIssued workWearIssued = findWorkWearIssuedById(id);
        WorkWear workWear = workWearService.findById(workWearIssued.getWorkWearId());
        workWear.setWorkWearStatus(WorkWear.NOT_ISSUE);
        workWearService.updateWorkWear(workWear);
        workWearIssuedRepository.deleteById(id);
        return  workWear.getWorkWearType().getValue() + " размер "
                + workWear.getWorkWearSize().getValue() + " возвращен на склад";
    }
}

