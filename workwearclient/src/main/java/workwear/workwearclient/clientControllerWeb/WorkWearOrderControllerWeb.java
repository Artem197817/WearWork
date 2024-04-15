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

    /**
     * Метод отображает список всех типов рабочей одежды, требующей замены.
     */
    @GetMapping("/workwear/replace/all")
    public String orderWearReplaceAll(Model model) {
        List<WorkWearTotalView> workWearTotalViewList = workWearOrderService.orderWearReplaceAll();
        model.addAttribute("workWearTotalViewList", workWearTotalViewList);
        return "order_total_wear_list";
    }

    /**
     * Метод отображает форму поиска типа рабочей одежды, требующей замены.
     * Добавляет в модель список типов рабочей одежды.
     */
    @GetMapping("/workwear/replace/type")
    public String orderWearReplaceType(Model model) {
        List<String> workWearTypeList = WorkWearType.getValuesString();
        model.addAttribute("workWearTypeList", workWearTypeList);
        return "order_wear_search_type";
    }

    /**
     * Метод отображает список рабочей одежды определенного типа, требующей замены.
     */
    @GetMapping("/replace/wear/type")
    public String orderReplaceWearType(@RequestParam String workWearType, Model model) {
        List<WorkWearTotalView> workWearTotalViewList = workWearOrderService.orderReplaceWearType(workWearType);
        model.addAttribute("workWearTotalViewList", workWearTotalViewList);
        return "order_total_wear_list";
    }

    /**
     * Метод отображает список всех типов рабочей одежды с дефицитом.
     */
    @GetMapping("/workwear/deficit/all")
    public String orderWearDeficitAll(Model model) {
        List<WorkWearOrderView> workWearOrderViewList = workWearOrderService.orderWearDeficitAll();
        model.addAttribute("workWearOrderViewList", workWearOrderViewList);
        return "order_deficit_wear_all";
    }

    /**
     * Метод отображает форму поиска типа рабочей одежды с дефицитом.
     * Добавляет в модель список типов рабочей одежды.
     */
    @GetMapping("/workwear/deficit/type")
    public String orderWearDeficitType(Model model) {
        List<String> workWearTypeList = WorkWearType.getValuesString();
        model.addAttribute("workWearTypeList", workWearTypeList);
        return "order_wear_deficit_type";
    }

    /**
     * Метод отображает список рабочей одежды определенного типа с дефицитом.
     */
    @GetMapping("/deficit/wear/type")
    public String orderDeficitWearType(@RequestParam String workWearType, Model model) {
        List<WorkWearOrderView> workWearOrderViewList = workWearOrderService.orderDeficitWearType(workWearType);
        model.addAttribute("workWearOrderViewList", workWearOrderViewList);
        return "order_deficit_wear_all";
    }

    /**
     * Метод отображает список всех типов рабочей обуви, требующей замены.
     */
    @GetMapping("/workshoes/replace/all")
    public String orderShoesReplaceAll(Model model) {
        List<WorkShoesTotalView> workShoesTotalViewList = workWearOrderService.orderShoesReplaceAll();
        model.addAttribute("workShoesTotalViewList", workShoesTotalViewList);
        return "order_total_shoes_list";
    }

    /**
     * Метод отображает форму поиска типа рабочей обуви, требующей замены.
     * Добавляет в модель список типов рабочей обуви.
     */
    @GetMapping("/workshoes/replace/type")
    public String orderShoesReplaceType(Model model) {
        List<String> workShoesTypeList = WorkShoesType.getValuesString();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        return "order_shoes_replace_type";
    }

    /**
     * Метод отображает список рабочей обуви определенного типа, требующей замены.
     */
    @GetMapping("/workshoes/type/replace")
    public String orderShoesTypeReplace(@RequestParam String workShoesType, Model model) {
        List<WorkShoesTotalView> workShoesTotalViewList = workWearOrderService.orderShoesTypeReplace(workShoesType);
        model.addAttribute("workShoesTotalViewList", workShoesTotalViewList);
        return "order_total_shoes_list";
    }

    /**
     * Метод отображает список всех типов рабочей обуви с дефицитом.
     */
    @GetMapping("/workshoes/deficit/all")
    public String orderShoesDeficitAll(Model model) {
        List<WorkShoesOrderView> workShoesOrderViewList = workWearOrderService.orderShoesDeficitAll();
        model.addAttribute("workShoesOrderViewList", workShoesOrderViewList);
        return "order_deficit_shoes_list";
    }

    /**
     * Метод отображает форму поиска типа рабочей обуви с дефицитом.
     * Добавляет в модель список типов рабочей обуви.
     */
    @GetMapping("/workshoes/deficit/type")
    public String orderShoesDeficitType(Model model) {
        List<String> workShoesTypeList = WorkShoesType.getValuesString();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        return "order_shoes_deficit_type";
    }

    /**
     * Метод отображает список рабочей обуви определенного типа с дефицитом.
     */
    @GetMapping("/workshoes/type/deficit")
    public String orderDeficitShoesType(@RequestParam String workShoesType, Model model) {
        List<WorkShoesOrderView> workShoesOrderViewList = workWearOrderService.orderDeficitShoesType(workShoesType);
        model.addAttribute("workShoesOrderViewList", workShoesOrderViewList);
        return "order_deficit_shoes_list";
    }

    /**
     * Метод отображает главную страницу заказов.
     */
    @GetMapping
    public String order() {
        return "order";
    }

    /**
     * Метод отображает страницу заказа рабочей одежды.
     */
    @GetMapping("/workwear")
    public String orderWear() {
        return "order_wear";
    }

    /**
     * Метод отображает страницу заказа рабочей обуви.
     */
    @GetMapping("/workshoes")
    public String orderShoes() {
        return "order_shoes";
    }

    /**
     * Метод отображает страницу замены рабочей одежды.
     */
    @GetMapping("workwear/replace")
    public String orderWearReplace() {
        return "order_wear_replace";
    }

    /**
     * Метод отображает страницу дефицита рабочей одежды.
     */
    @GetMapping("/workwear/deficit")
    public String orderWearDeficit() {
        return "order_wear_deficit";
    }

    /**
     * Метод отображает страницу замены рабочей обуви.
     */
    @GetMapping("/workshoes/replace")
    public String orderShoesReplace() {
        return "order_shoes_replace";
    }

    /**
     * Метод отображает страницу дефицита рабочей обуви.
     */
    @GetMapping("/workshoes/deficit")
    public String orderShoesDeficit() {
        return "order_shoes_deficit";
    }
}
