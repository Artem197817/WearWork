package workwear.workshoes.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workwear.workshoes.model.WorkShoesTotal;
import workwear.workshoes.model.enumerated.WorkShoesType;
import workwear.workshoes.service.WorkShoesTotalService;

import java.util.List;

@RestController
@RequestMapping("/work_shoes_total")
@AllArgsConstructor
public class WorkShoesTotalController {

    private final WorkShoesTotalService workShoesTotalService;

    @GetMapping("/work_shoes_total_type/{workShoesType}")
    public List<WorkShoesTotal> findWorkShoesByTypeSortedNumber(@PathVariable WorkShoesType workShoesType) {
        return workShoesTotalService.findWorkShoesByTypeSortedNumber(workShoesType);
    }

    @GetMapping("/work_shoes_total_size/{workShoesSize}")
    public List<WorkShoesTotal> findWorkShoesBySizeSortedNumber(@PathVariable Integer workShoesSize) {
        return workShoesTotalService.findWorkShoesBySizeSortedNumber(workShoesSize);
    }

    @GetMapping("/work_shoes_total_all")
    public List<WorkShoesTotal> findAllWorkShoesSortedNumber() {
        return workShoesTotalService.findAllWorkShoesSortedNumber();
    }

}