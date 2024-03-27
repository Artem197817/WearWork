package workwear.workwearclient.clientApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import workwear.workwearclient.model.WorkWear;
import workwear.workwearclient.model.modelEnum.WorkWearSize;
import workwear.workwearclient.model.modelEnum.WorkWearType;

import java.util.List;

@FeignClient(name = "workWear" ,url = "http://localhost:8089/work_wear")
public interface WorkWearApiClient {

    @GetMapping
    List<WorkWear> findAllWorkWear();

    @PostMapping("/save_work_wear")
    String saveWorkWear(@RequestBody WorkWear workWear);

    @GetMapping("find/{id}")
    WorkWear findById(@PathVariable Long id);

    @PutMapping("/update_work_wear")
    WorkWear updateWorkWear(@RequestBody WorkWear workWear);

    @DeleteMapping("/delete_work_wear/{id}")
    void deleteWorkWear(@PathVariable Long id);

    @PostMapping("/save_all_wear")
    List<WorkWear> saveAllWorkWear(@RequestBody List<WorkWear> workWearList);

    @GetMapping("/work_wear_model/{modelWorkWear}")
    List<WorkWear> findAllWorkWearByModelWorkWear(@PathVariable String modelWorkWear);

    @GetMapping("/work_wear_type/{workWearType}")
    List<WorkWear> findAllWorkWearByWorkWearType(@PathVariable WorkWearType workWearType);

    @GetMapping("/work_wear_size/{workWearSize}")
    List<WorkWear> findAllWorkWearByWorkWearSize(@PathVariable WorkWearSize workWearSize);

}
