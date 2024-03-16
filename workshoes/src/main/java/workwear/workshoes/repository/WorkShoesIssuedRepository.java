package workwear.workshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workwear.workshoes.model.WorkShoesIssued;

import java.util.List;

public interface WorkShoesIssuedRepository extends JpaRepository<WorkShoesIssued,Long> {

    List<WorkShoesIssued> findWorkShoesIssuedByEmployeeId (Long id);
}