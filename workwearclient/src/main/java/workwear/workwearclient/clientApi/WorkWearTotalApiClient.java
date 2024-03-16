package workwear.workwearclient.clientApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import workwear.workwearclient.model.WorkWearTotal;
import workwear.workwearclient.model.modelEnum.WorkWearSize;
import workwear.workwearclient.model.modelEnum.WorkWearType;

import java.util.List;

@FeignClient(name = "workWearTotal", url = "http://localhost:8089/work_wear_total")
public interface WorkWearTotalApiClient {

    @GetMapping("/work_wear_total_type/{workWearType}")
    List<WorkWearTotal> findWorkWearByTypeSortedNumber(@PathVariable WorkWearType workWearType);

    @GetMapping("/work_wear_total_size/{workWearSize}")
    List<WorkWearTotal> findWorkWearBySizeSortedNumber(@PathVariable WorkWearSize workWearSize);

    @GetMapping("/work_wear_total_all")
    List<WorkWearTotal> findAllWorkWearSortedNumber();
}
