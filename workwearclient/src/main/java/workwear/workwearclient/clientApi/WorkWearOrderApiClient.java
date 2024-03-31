package workwear.workwearclient.clientApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import workwear.workwearclient.model.WorkWearOrder;
import workwear.workwearclient.model.WorkWearTotal;
import workwear.workwearclient.model.modelEnum.WorkWearType;

import java.util.List;

@FeignClient(name = "workWearOrder", url = "http://localhost:8089/work_wear_order")
public interface WorkWearOrderApiClient {

    @GetMapping("/total_replaced_all")
    List<WorkWearTotal> findWorkWearAllReplaced();

    @GetMapping("total_replaced_type")
    List<WorkWearTotal> findWorkWearReplacedByType(@RequestParam WorkWearType workWearType);

    @GetMapping("order_missing_all")
    List<WorkWearOrder> searchForMissingDimensionsAll();

    @GetMapping("order_missing_type")
    List<WorkWearOrder> searchForMissingDimensionsByType(@RequestParam WorkWearType workWearType);
}
