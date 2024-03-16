package workwear.workwear.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workwear.workwear.model.WorkWearTotal;
import workwear.workwear.model.enumerated.WorkWearSize;
import workwear.workwear.model.enumerated.WorkWearType;
import workwear.workwear.service.WorkWearTotalService;

import java.util.List;

@RestController
@RequestMapping("/work_wear_total")
@AllArgsConstructor
public class WorkWearTotalController {

    private final WorkWearTotalService workWearTotalService;

    @GetMapping("/work_wear_total_type/{workWearType}")
    public List<WorkWearTotal> findWorkWearByTypeSortedNumber(@PathVariable WorkWearType workWearType) {
        return workWearTotalService.findWorkWearByTypeSortedNumber(workWearType);
    }

    @GetMapping("/work_wear_total_size/{workWearSize}")
    public List<WorkWearTotal> findWorkWearBySizeSortedNumber(@PathVariable WorkWearSize workWearSize) {
        return workWearTotalService.findWorkWearBySizeSortedNumber(workWearSize);
    }

    @GetMapping("/work_wear_total_all")
    public List<WorkWearTotal> findAllWorkWearSortedNumber() {
        return workWearTotalService.findAllWorkWearSortedNumber();
    }
}
