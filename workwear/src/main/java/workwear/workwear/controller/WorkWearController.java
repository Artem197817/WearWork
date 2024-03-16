package workwear.workwear.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import workwear.workwear.model.WorkWear;
import workwear.workwear.model.enumerated.WorkWearSize;
import workwear.workwear.model.enumerated.WorkWearType;
import workwear.workwear.service.WorkWearService;

import java.util.List;

@RestController
@RequestMapping("/work_wear")
@Data
public class WorkWearController {
    private final WorkWearService workWearService;

    @GetMapping
    public List<WorkWear> findAllWorkWear() {
        return workWearService.findAllWorkWear();
    }

    @PostMapping("save_work_wear")
    public String saveWorkWear(@RequestBody WorkWear workWear) {
        workWearService.saveWorkWear(workWear);
        return "WorkShoes successfully saved";
    }

    @GetMapping("/{id}")
    public WorkWear findByID(@PathVariable Long id) {
        return workWearService.findById(id);
    }

    @PutMapping("/update_work_wear")
    public WorkWear updateWorkWear(@RequestBody WorkWear workWear) {
        return workWearService.updateWorkWear(workWear);
    }

    @DeleteMapping("/delete_work_wear/{id}")
    public void deleteWorkWear(@PathVariable Long id) {
        workWearService.deleteWorkWearById(id);
    }

    @PostMapping("save_all_wear")
    public List<WorkWear> saveAllWorkWear(@RequestBody List<WorkWear> workWearList) {
        return workWearService.saveAllWorkWear(workWearList);
    }

    @GetMapping("/work_wear_model/{modelWorkWear}")
    public List<WorkWear> findAllWorkWearByModelWorkWear(@PathVariable String modelWorkWear) {
        return workWearService.findAllWorkWearByModelWorkWear(modelWorkWear);
    }

    @GetMapping("/work_wear_type/{workWearType}")
    public List<WorkWear> findAllWorkWearByWorkWearType(@PathVariable WorkWearType workWearType) {
        return workWearService.findAllWorkWearByWorkWearType(workWearType);
    }

    @GetMapping("/work_wear_size/{workWearSize}")
    public List<WorkWear> findAllWorkWearByWorkWearSize(@PathVariable WorkWearSize workWearSize) {
        return workWearService.findAllWorkWearByWorkWearSize(workWearSize);
    }
}
