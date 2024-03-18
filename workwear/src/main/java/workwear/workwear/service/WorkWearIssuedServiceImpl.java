package workwear.workwear.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workwear.workwear.model.WorkWear;
import workwear.workwear.model.WorkWearIssued;
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
    public Map<WorkWearIssued,WorkWear> findWorkWearIssuedEmployee(Long id) {
        List<WorkWearIssued> workWearIssuedList = findWorkWearIssuedByEmployeeId(id);
        Map<WorkWearIssued,WorkWear>  workWearMap = new HashMap<>();
        if (workWearIssuedList.isEmpty()) return new HashMap<>();
        for (WorkWearIssued wwi: workWearIssuedList){
            workWearMap.put(wwi,workWearService.findById(wwi.getWorkWearId()));
        }
        return workWearMap;
    }
}

