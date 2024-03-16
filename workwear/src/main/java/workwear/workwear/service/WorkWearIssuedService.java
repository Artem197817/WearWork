package workwear.workwear.service;

import workwear.workwear.model.WorkWearIssued;

import java.util.List;

public interface WorkWearIssuedService {

    List<WorkWearIssued> findAllWorkWearIssued();

    WorkWearIssued findWorkWearIssuedById(Long id);

    String saveWorkWearIssued(WorkWearIssued workWearIssued);

    String updateWorkWearIssued(WorkWearIssued workWearIssued);

    void deleteWorkWearIssuedById(Long id);

    List<WorkWearIssued> findWorkWearIssuedByEmployeeId(Long id);

    List<WorkWearIssued> findWorkWearIssuedToBeReplaced();
}
