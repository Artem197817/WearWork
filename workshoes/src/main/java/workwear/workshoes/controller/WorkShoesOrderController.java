package workwear.workshoes.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import workwear.workshoes.model.WorkShoesOrder;
import workwear.workshoes.model.WorkShoesTotal;
import workwear.workshoes.model.enumerated.WorkShoesType;
import workwear.workshoes.service.WorkShoesOrderService;

import java.util.List;

@RestController
@RequestMapping("/work_shoes_order")
@AllArgsConstructor
public class WorkShoesOrderController {

    private final WorkShoesOrderService workShoesOrderService;

    @GetMapping("/total_replaced_type")
    public List<WorkShoesTotal> findWorShoesReplacedByType(@RequestParam WorkShoesType workShoesType){
        return workShoesOrderService.findWorShoesReplacedByType(workShoesType);
    }

    @GetMapping("/total_replaced_all")
    public List<WorkShoesTotal>  findWorkShoesAllReplaced(){
        return workShoesOrderService.findWorkShoesAllReplaced();
    }

    @GetMapping("order_missing_type")
    public List<WorkShoesOrder> searchForMissingDimensionsByType(@RequestParam WorkShoesType workShoesType){
        return workShoesOrderService.searchForMissingDimensionsByType(workShoesType);
    }

    @GetMapping("order_missing_all")
    public List<WorkShoesOrder> searchForMissingDimensionsAll(){
        return workShoesOrderService.searchForMissingDimensionsAll();
    }
}
