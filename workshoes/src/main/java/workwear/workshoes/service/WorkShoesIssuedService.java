package workwear.workshoes.service;

import workwear.workshoes.model.WorkShoesIssued;

import java.util.List;

public interface WorkShoesIssuedService {

    List<WorkShoesIssued> findAllWorkShoesIssued();

    String saveWorkShoesIssued(WorkShoesIssued workShoesIssued);

    WorkShoesIssued findById(Long id);

    WorkShoesIssued updateWorkShoesIssued(WorkShoesIssued workShoesIssued);

    void deleteWorkShoesIssued(Long id);

    List<WorkShoesIssued> findWorkShoesIssuedByEmployeeId(Long id);

    List<WorkShoesIssued> findWorkShoesToBeReplaced();
}
