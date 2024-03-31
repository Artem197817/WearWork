package workwear.workwearclient.clientApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import workwear.workwearclient.model.WorkShoesOrder;
import workwear.workwearclient.model.WorkShoesTotal;
import workwear.workwearclient.model.modelEnum.WorkShoesType;

import java.util.List;

@FeignClient(name = "workShoesOrder", url = "http://localhost:8089/work_shoes_order")
public interface WorkShoesOrderApiClient {

    @GetMapping("/total_replaced_type")
    List<WorkShoesTotal> findWorShoesReplacedByType(@RequestParam WorkShoesType workShoesType);

    @GetMapping("/total_replaced_all")
    List<WorkShoesTotal> findWorkShoesAllReplaced();

    @GetMapping("order_missing_type")
    List<WorkShoesOrder> searchForMissingDimensionsByType(@RequestParam WorkShoesType workShoesType);

    @GetMapping("order_missing_all")
    List<WorkShoesOrder> searchForMissingDimensionsAll();
}
