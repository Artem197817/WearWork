package workwear.workshoes.service;

import workwear.workshoes.model.WorkShoes;
import workwear.workshoes.model.WorkShoesIssued;

import java.util.List;
import java.util.Map;

public interface WorkShoesIssuedService {

    List<WorkShoesIssued> findAllWorkShoesIssued();

    String saveWorkShoesIssued(WorkShoesIssued workShoesIssued);

    WorkShoesIssued findById(Long id);

    WorkShoesIssued updateWorkShoesIssued(WorkShoesIssued workShoesIssued);

    void deleteWorkShoesIssued(Long id);

    List<WorkShoesIssued> findWorkShoesIssuedByEmployeeId(Long id);

    List<WorkShoesIssued> findWorkShoesToBeReplaced();

    Map<WorkShoesIssued,WorkShoes> findWorkShoesIssuedEmployee(Long id);
}
