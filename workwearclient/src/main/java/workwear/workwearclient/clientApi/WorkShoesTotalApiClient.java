package workwear.workwearclient.clientApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import workwear.workwearclient.model.WorkShoesTotal;
import workwear.workwearclient.model.modelEnum.WorkShoesType;

import java.util.List;

@FeignClient(name = "workShoesTotal", url = "http://localhost:8089/work_shoes_total")
public interface WorkShoesTotalApiClient {

    @GetMapping("/work_shoes_total_type/{workShoesType}")
    List<WorkShoesTotal> findWorkShoesByTypeSortedNumber(@PathVariable WorkShoesType workShoesType);

    @GetMapping("/work_shoes_total_size/{workShoesSize}")
    List<WorkShoesTotal> findWorkShoesBySizeSortedNumber(@PathVariable Integer workShoesSize);

    @GetMapping("/work_shoes_total_all")
    List<WorkShoesTotal> findAllWorkShoesSortedNumber();
}
