package workwear.workwearclient.clientApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import workwear.workwearclient.model.WorkShoesIssued;
import workwear.workwearclient.model.modelview.WorkShoesIssuedView;

import java.util.List;

@FeignClient(name = "workShoesIssued", url = "http://localhost:8089/work_shoes_issued")
public interface WorkShoesIssuedApiClient {

    @GetMapping("/work_shoes_issued_all")
    List<WorkShoesIssued> findAllWorkShoesIssued();

    @PostMapping("/save_work_shoes_issued")
    String saveWorkShoesIssued(@RequestBody WorkShoesIssued workShoesIssued);

    @GetMapping("/work_shoes_issued_by_id/(id)")
    WorkShoesIssued findById(@PathVariable Long id);

    @PutMapping("/work_shoes_issued_update/{workShoesIssued}")
    WorkShoesIssued updateWorkShoesIssued(@RequestBody WorkShoesIssued workShoesIssued);

    @DeleteMapping("/delete_work_shoes_issued/{id}")
    void deleteWorkShoesIssued(@PathVariable Long id);

    @GetMapping("/work_shoes_issued_by_id_employee/{id}")
    List<WorkShoesIssued> findWorkShoesIssuedByEmployeeID(@PathVariable Long id);

    @GetMapping("/work_shoes_to_be_replaced")
    List<WorkShoesIssued> findWorkShoesToBeReplaced();

    @GetMapping("/work_shoes_issued_employee/{id}")
    List<WorkShoesIssuedView> findWorkShoesIssuedEmployee(@PathVariable Long id);
}
