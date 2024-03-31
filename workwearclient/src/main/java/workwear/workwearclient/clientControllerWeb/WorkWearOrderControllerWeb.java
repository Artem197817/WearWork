package workwear.workwearclient.clientControllerWeb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import workwear.workwearclient.model.modelEnum.WorkShoesType;
import workwear.workwearclient.model.modelEnum.WorkWearType;
import workwear.workwearclient.model.modelview.WorkShoesOrderView;
import workwear.workwearclient.model.modelview.WorkShoesTotalView;
import workwear.workwearclient.model.modelview.WorkWearOrderView;
import workwear.workwearclient.model.modelview.WorkWearTotalView;
import workwear.workwearclient.service.WorkWearOrderService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/order")
public class WorkWearOrderControllerWeb {

    private final WorkWearOrderService workWearOrderService;

    @GetMapping("/workwear/replace/all")
    public String orderWearReplaceAll(Model model) {
        List<WorkWearTotalView> workWearTotalViewList = workWearOrderService.orderWearReplaceAll();
        model.addAttribute("workWearTotalViewList", workWearTotalViewList);
        return "order_total_wear_list";
    }

    @GetMapping("/workwear/replace/type")
    public String orderWearReplaceType(Model model) {
        List<String> workWearTypeList = WorkWearType.getValuesString();
        model.addAttribute("workWearTypeList", workWearTypeList);
        return "order_wear_search_type";
    }

    @GetMapping("/replace/wear/type")
    public String orderReplaceWearType(@RequestParam String workWearType, Model model) {
        List<WorkWearTotalView> workWearTotalViewList = workWearOrderService.orderReplaceWearType(workWearType);
        model.addAttribute("workWearTotalViewList", workWearTotalViewList);
        return "order_total_wear_list";
    }

    @GetMapping("/workwear/deficit/all")
    public String orderWearDeficitAll(Model model) {
        List<WorkWearOrderView> workWearOrderViewList = workWearOrderService.orderWearDeficitAll();
        model.addAttribute("workWearOrderViewList", workWearOrderViewList);
        return "order_deficit_wear_all";
    }

    @GetMapping("/workwear/deficit/type")
    public String orderWearDeficitType(Model model) {
        List<String> workWearTypeList = WorkWearType.getValuesString();
        model.addAttribute("workWearTypeList", workWearTypeList);
        return "order_wear_deficit_type";
    }

    @GetMapping("/deficit/wear/type")
    public String orderDeficitWearType(@RequestParam String workWearType, Model model) {
        List<WorkWearOrderView> workWearOrderViewList = workWearOrderService.orderDeficitWearType(workWearType);
        model.addAttribute("workWearOrderViewList", workWearOrderViewList);
        return "order_deficit_wear_all";
    }

    @GetMapping("/workshoes/replace/all")
    public String orderShoesReplaceAll(Model model) {
        List<WorkShoesTotalView> workShoesTotalViewList = workWearOrderService.orderShoesReplaceAll();
        model.addAttribute("workShoesTotalViewList", workShoesTotalViewList);
        return "order_total_shoes_list";
    }

    @GetMapping("/workshoes/replace/type")
    public String orderShoesReplaceType(Model model) {
        List<String> workShoesTypeList = WorkShoesType.getValuesString();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        return "order_shoes_replace_type";
    }

    @GetMapping("/workshoes/type/replace")
    public String orderShoesTypeReplace(@RequestParam String workShoesType, Model model) {
        List<WorkShoesTotalView> workShoesTotalViewList = workWearOrderService.orderShoesTypeReplace(workShoesType);
        model.addAttribute("workShoesTotalViewList", workShoesTotalViewList);
        return "order_total_shoes_list";
    }

    @GetMapping("/workshoes/deficit/all")
    public String orderShoesDeficitAll(Model model) {
        List<WorkShoesOrderView> workShoesOrderViewList = workWearOrderService.orderShoesDeficitAll();
        model.addAttribute("workShoesOrderViewList", workShoesOrderViewList);
        return "order_deficit_shoes_list";
    }

    @GetMapping("/workshoes/deficit/type")
    public String orderShoesDeficitType(Model model) {
        List<String> workShoesTypeList = WorkShoesType.getValuesString();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        return "order_shoes_deficit_type";
    }

    @GetMapping("/workshoes/type/deficit")
    public String orderDeficitShoesType(@RequestParam String workShoesType, Model model) {
        List<WorkShoesOrderView> workShoesOrderViewList = workWearOrderService.orderDeficitShoesType(workShoesType);
        model.addAttribute("workShoesOrderViewList", workShoesOrderViewList);
        return "order_deficit_shoes_list";
    }

    @GetMapping
    public String order() {
        return "order";
    }

    @GetMapping("/workwear")
    public String orderWear() {
        return "order_wear";
    }

    @GetMapping("/workshoes")
    public String orderShoes() {
        return "order_shoes";
    }

    @GetMapping("workwear/replace")
    public String orderWearReplace() {
        return "order_wear_replace";
    }

    @GetMapping("/workwear/deficit")
    public String orderWearDeficit() {
        return "order_wear_deficit";
    }

    @GetMapping("/workshoes/replace")
    public String orderShoesReplace() {
        return "order_shoes_replace";
    }

    @GetMapping("/workshoes/deficit")
    public String orderShoesDeficit() {
        return "order_shoes_deficit";
    }


}
