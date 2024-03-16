package workwear.workwearclient.clientApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import workwear.workwearclient.model.WorkShoes;
import workwear.workwearclient.model.modelEnum.WorkShoesType;

import java.util.List;

@FeignClient(name = "workShoes",url = "http://localhost:8089/work_shoes")
public interface WorkShoesApiClient {

    @GetMapping
    List<WorkShoes> findAllWorkShoes();

    @GetMapping("/{id}")
    WorkShoes findById(@PathVariable Long id);

    @PostMapping("/save_shoes")
    String saveWorkShoes(@RequestBody WorkShoes workShoes);


    @DeleteMapping("/delete_work_shoes/{id}")
    void deleteWorkShoes(@PathVariable long id);


    @GetMapping("/work_shoes_size/{workShoesSize}")
    List<WorkShoes> findAllWorkShoesByWorkShoesSize(@PathVariable Integer workShoesSize);

    @GetMapping("/work_shoes_type/{workShoesType}")
    List<WorkShoes> findAllWorkShoesByWorkShoesType(@PathVariable WorkShoesType workShoesType);

    @PostMapping ("/save_all_work_shoes")
    void saveAllWorkShoes (@RequestBody List<WorkShoes> workShoesList);
}
