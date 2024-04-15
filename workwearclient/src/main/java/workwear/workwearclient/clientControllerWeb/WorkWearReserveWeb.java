package workwear.workwearclient.clientControllerWeb;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import workwear.workwearclient.controller.WorkShoesTotalController;
import workwear.workwearclient.controller.WorkWearTotalController;
import workwear.workwearclient.model.modelEnum.WorkShoesType;
import workwear.workwearclient.model.modelEnum.WorkWearSize;
import workwear.workwearclient.model.modelEnum.WorkWearType;
import workwear.workwearclient.model.modelview.WorkShoesTotalView;
import workwear.workwearclient.model.modelview.WorkWearTotalView;
import workwear.workwearclient.service.WorkShoesService;
import workwear.workwearclient.service.WorkWearReserveService;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Controller
@AllArgsConstructor
@RequestMapping("/reserve")
public class WorkWearReserveWeb {

    private final WorkShoesTotalController workShoesTotalController;
    private final WorkWearTotalController workWearTotalController;
    private final WorkWearReserveService workWearReserveService;
    private final WorkShoesService workShoesService;

    private final Timer timer = Metrics.timer("reserve_wear_method");
    private final Timer timer1 = Metrics.timer("reserve_shoes_all");

    @GetMapping("/workwear/all")
    public String reserveWearAll(Model model) {
        timer.record(60, TimeUnit.MILLISECONDS);
        List<WorkWearTotalView> workWearTotalViewList = workWearReserveService.createWorWearTotalView(workWearTotalController
                .findAllWorkWearSortedNumber());
        model.addAttribute("workWearTotalViewList", workWearTotalViewList);
        return "reserve_wear_list";
    }

    @GetMapping("/workshoes/all")
    public String reserveShoesAll(Model model) {
        timer1.record(60, TimeUnit.MILLISECONDS);
        List<WorkShoesTotalView> workShoesTotalViewList = workWearReserveService.createWorkShoesTotalView((workShoesTotalController
                .findAllWorkShoesSortedNumber()));
        model.addAttribute("workShoesTotalViewList", workShoesTotalViewList);
        return "reserve_shoes_list";
    }

    @GetMapping("/workshoes/type")
    public String reserveShoesType(@RequestParam String workShoesType, Model model) {
        WorkShoesType workShoesType1 = WorkShoesType.getType(workShoesType);
        List<WorkShoesTotalView> workShoesTotalViewList = workWearReserveService
                .createWorkShoesTotalView(workShoesTotalController.findWorkShoesByTypeSortedNumber(workShoesType1));
        model.addAttribute("workShoesTotalViewList", workShoesTotalViewList);
        return "reserve_shoes_list";
    }

    @GetMapping("/workshoes/size")
    public String reserveShoesSize(@RequestParam Integer workShoesSize, Model model) {
        List<WorkShoesTotalView> workShoesTotalViewList = workWearReserveService
                .createWorkShoesTotalView(workShoesTotalController.findWorkShoesBySizeSortedNumber(workShoesSize));
        model.addAttribute("workShoesTotalViewList", workShoesTotalViewList);
        return "reserve_shoes_list";
    }

    @GetMapping("/workwear/type")
    public String reserveWearType(@RequestParam String workWearType, Model model) {
        WorkWearType workWearType1 = WorkWearType.getType(workWearType);
        List<WorkWearTotalView> workWearTotalViewList = workWearReserveService
                .createWorWearTotalView(workWearTotalController.findWorkWearByTypeSortedNumber(workWearType1));
        model.addAttribute("workWearTotalViewList", workWearTotalViewList);
        return "reserve_wear_list";
    }

    @GetMapping("/workwear/size")
    public String reserveWearSize(@RequestParam String workWearSize, Model model) {
        WorkWearSize workWearSize1 = WorkWearSize.getType(workWearSize);
        List<WorkWearTotalView> workWearTotalViewList = workWearReserveService
                .createWorWearTotalView(workWearTotalController.findWorkWearBySizeSortedNumber(workWearSize1));
        model.addAttribute("workWearTotalViewList", workWearTotalViewList);
        return "reserve_wear_list";
    }

    @GetMapping("/wear_param")
    public String searchParamWear(Model model) {
        List<String> workWearTypeList = WorkWearType.getValuesString();
        model.addAttribute("workWearTypeList", workWearTypeList);
        List<String> workWearSizeList = WorkWearSize.getValuesString();
        model.addAttribute("workWearSizeList", workWearSizeList);
        return "reserve_wear_param";
    }

    @GetMapping("/shoes_param")
    public String searchParamShoes(Model model) {
        List<String> workShoesTypeList = WorkShoesType.getValuesString();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        List<Integer> workShoesSizeList = workShoesService.createSizeList();
        model.addAttribute("workShoesSizeList", workShoesSizeList);
        return "reserve_shoes_param";
    }
    @GetMapping
    public String reserve() {
        return "reserve";
    }

    @GetMapping("/workwear")
    public String reserveWear() {
        return "reserve_wear";
    }

    @GetMapping("/workshoes")
    public String reserveShoes() {
        return "reserve_shoes";
    }
}
