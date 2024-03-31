package workwear.workwear.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import workwear.workwear.model.WorkWearOrder;
import workwear.workwear.model.WorkWearTotal;
import workwear.workwear.model.enumerated.WorkWearType;
import workwear.workwear.service.WorkWearOrderService;

import java.util.List;

@RestController
@RequestMapping("/work_wear_order")
@AllArgsConstructor
public class WorkWearOrderController {

    private final WorkWearOrderService workWearOrderService;

    @GetMapping("total_replaced_all")
    public List<WorkWearTotal> findWorkWearAllReplaced(){
        return workWearOrderService.findWorkWearAllReplaced();
    }

    @GetMapping("total_replaced_type")
    public List<WorkWearTotal>findWorkWearReplacedByType(@RequestParam WorkWearType workWearType){
        return workWearOrderService.findWorkWearReplacedByType(workWearType);
    }

    @GetMapping("order_missing_all")
    public List<WorkWearOrder> searchForMissingDimensionsAll(){
        return workWearOrderService.searchForMissingDimensionsAll();
    }

    @GetMapping("order_missing_type")
    public  List<WorkWearOrder> searchForMissingDimensionsByType(@RequestParam WorkWearType workWearType){
        return workWearOrderService.searchForMissingDimensionsByType(workWearType);
    }
}
