package workwear.workshoes.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import workwear.workshoes.model.WorkShoes;
import workwear.workshoes.model.WorkShoesIssued;
import workwear.workshoes.service.WorkShoesIssuedService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/work_shoes_issued")
@Data
public class WorkShoesIssuedController {

    private final WorkShoesIssuedService workShoesIssuedService;

    @GetMapping("work_shoes_issued_all")
    public List<WorkShoesIssued> findAllWorkShoesIssued() {
        return workShoesIssuedService.findAllWorkShoesIssued();
    }

    @PostMapping("save_work_shoes_issued")
    public String saveWorkShoesIssued(@RequestBody WorkShoesIssued workShoesIssued) {
        workShoesIssuedService.saveWorkShoesIssued(workShoesIssued);
        return "WorkShoesIssued save";
    }

    @GetMapping("work_shoes_issued_by_id/(id)")
    public WorkShoesIssued findById(@PathVariable Long id) {
        return workShoesIssuedService.findById(id);
    }

    @PutMapping("work_shoes_issued_update/{workShoesIssued}")
    public WorkShoesIssued updateWorkShoesIssued(@RequestBody WorkShoesIssued workShoesIssued) {
        return null;
    }

    @DeleteMapping("delete_work_shoes_issued/{id}")
    public void deleteWorkShoesIssued(@PathVariable Long id) {
        workShoesIssuedService.deleteWorkShoesIssued(id);
    }

    @GetMapping("work_shoes_issued_by_id_employee/{id}")
    public List<WorkShoesIssued> findWorkShoesIssuedByEmployeeID(@PathVariable Long id) {
        return workShoesIssuedService.findWorkShoesIssuedByEmployeeId(id);
    }

    @GetMapping("work_shoes_to_be_replaced")
    public List<WorkShoesIssued> findWorkShoesToBeReplaced() {
        return workShoesIssuedService.findWorkShoesToBeReplaced();
    }
    @GetMapping("/work_shoes_issued_employee/{id}")
    public Map<WorkShoesIssued,WorkShoes> findWorkShoesIssuedEmployee (@PathVariable Long id){
        return workShoesIssuedService.findWorkShoesIssuedEmployee(id);
    }
}
