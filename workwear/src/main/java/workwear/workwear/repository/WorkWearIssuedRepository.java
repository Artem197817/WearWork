package workwear.workwear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workwear.workwear.model.WorkWearIssued;

import java.util.List;

public interface WorkWearIssuedRepository extends JpaRepository<WorkWearIssued, Long> {

    List<WorkWearIssued> findWorkWearIssuedByEmployeeId(Long id);
}
