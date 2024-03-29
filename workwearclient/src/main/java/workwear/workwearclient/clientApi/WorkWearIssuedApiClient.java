package workwear.workwearclient.clientApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import workwear.workwearclient.model.WorkWearIssued;
import workwear.workwearclient.model.modelview.WorkWearIssuedView;

import java.util.List;

@FeignClient(name = "workWearIssued", url = "http://localhost:8089/work_wear_issued")
public interface WorkWearIssuedApiClient {

    @GetMapping("/work_wear_issued_all")
    List<WorkWearIssued> findAllWorkWearIssued();

    @GetMapping("/work_wear_issued_by_id/{id}")
    WorkWearIssued findWorkWearIssuedById(@PathVariable Long id);

    @PostMapping("/save_work_wear_issued")
    void saveWorkWearIssued(@RequestBody WorkWearIssued workWearIssued);

    @PutMapping("/update_work_wear_issued")
    String updateWorkWearIssued(@RequestBody WorkWearIssued workWearIssued);

    @DeleteMapping("/delete_work_wear_issued/{id}")
    void deleteWorkWearIssued(@PathVariable Long id);

    @GetMapping("/work_wear_issued_by_employee_id/{id}")
    List<WorkWearIssued> findWorkWearIssuedByEmployeeId(@PathVariable Long id);

    @GetMapping("/work_wear_issued_to_be_replace")
    List<WorkWearIssued> findWorkWearIssuedToBeReplaced();

    @GetMapping("/work_wear_issued_employee_id/{id}")
    List<WorkWearIssuedView> findWorkWearIssuedEmployee(@PathVariable Long id);

    @PutMapping("work_wear_return_storage/{id}")
    String returnWorkWearOnStorage(@PathVariable Long id);
}
