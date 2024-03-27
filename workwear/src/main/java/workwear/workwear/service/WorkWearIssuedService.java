package workwear.workwear.service;

import org.springframework.web.bind.annotation.PathVariable;
import workwear.workwear.model.WorkWear;
import workwear.workwear.model.WorkWearIssued;
import workwear.workwear.model.WorkWearIssuedView;

import java.util.List;
import java.util.Map;

public interface WorkWearIssuedService {

    List<WorkWearIssued> findAllWorkWearIssued();

    WorkWearIssued findWorkWearIssuedById(Long id);

    String saveWorkWearIssued(WorkWearIssued workWearIssued);

    String updateWorkWearIssued(WorkWearIssued workWearIssued);

    void deleteWorkWearIssuedById(Long id);

    List<WorkWearIssued> findWorkWearIssuedByEmployeeId(Long id);

    List<WorkWearIssued> findWorkWearIssuedToBeReplaced();

    List<WorkWearIssuedView> findWorkWearIssuedEmployee(Long id);

    WorkWearIssued findWorkWearIssuedByWorkWearId(Long id);

    String returnWorkWearOnStorage(@PathVariable Long id);
}
