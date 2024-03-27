package workwear.workwear.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import workwear.workwear.model.WorkWearIssued;
import workwear.workwear.model.WorkWearIssuedView;
import workwear.workwear.service.WorkWearIssuedService;
import workwear.workwear.service.WorkWearService;

import java.util.List;

@RestController
@RequestMapping("/work_wear_issued")
@AllArgsConstructor
public class WorkWearIssuedController {

    private final WorkWearIssuedService workWearIssuedService;

    @GetMapping("work_wear_issued_all")
    public List<WorkWearIssued> findAllWorkWearIssued() {
        return workWearIssuedService.findAllWorkWearIssued();
    }

    @GetMapping("work_wear_issued_by_id/{id}")
    public WorkWearIssued findWorkWearIssuedById(@PathVariable Long id) {
        return workWearIssuedService.findWorkWearIssuedById(id);
    }

    @PostMapping("save_work_wear_issued")
    public String saveWorkWearIssued(@RequestBody WorkWearIssued workWearIssued) {
        return workWearIssuedService.saveWorkWearIssued(workWearIssued);
    }

    @PutMapping("update_work_wear_issued")
    public String updateWorkWearIssued(@RequestBody WorkWearIssued workWearIssued) {
        return workWearIssuedService.updateWorkWearIssued(workWearIssued);
    }

    @DeleteMapping("delete_work_wear_issued/{id}")
    public void deleteWorkWearIssued(@PathVariable Long id) {
        workWearIssuedService.deleteWorkWearIssuedById(id);
    }

    @GetMapping("work_wear_issued_by_employee_id/{id}")
    public List<WorkWearIssued> findWorkWearIssuedByEmployeeId(@PathVariable Long id) {
        return workWearIssuedService.findWorkWearIssuedByEmployeeId(id);
    }

    @GetMapping("work_wear_issued_to_be_replace")
    public List<WorkWearIssued> findWorkWearIssuedToBeReplaced() {
        return workWearIssuedService.findWorkWearIssuedToBeReplaced();
    }

    @GetMapping("/work_wear_issued_employee_id/{id}")
    public List<WorkWearIssuedView> findWorkWearIssuedEmployee(@PathVariable Long id) {
        return workWearIssuedService.findWorkWearIssuedEmployee(id);
    }

    @PutMapping("work_wear_return_storage/{id}")
    public String returnWorkWearOnStorage(@PathVariable Long id) {
        return workWearIssuedService.returnWorkWearOnStorage(id);
    }
}

