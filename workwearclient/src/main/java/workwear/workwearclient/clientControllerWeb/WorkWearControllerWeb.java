package workwear.workwearclient.clientControllerWeb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import workwear.workwearclient.controller.WorkWearController;
import workwear.workwearclient.model.WorkWear;
import workwear.workwearclient.model.modelEnum.*;
import workwear.workwearclient.model.modelview.WorkWearArrival;
import workwear.workwearclient.model.modelview.WorkWearView;
import workwear.workwearclient.service.WorkWearService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/workwear")
public class WorkWearControllerWeb {

    private final WorkWearController workWearController;
    private final WorkWearService workWearService;

    /**
     Метод отображает форму для создания новой рабочей одежды.
     Добавляет в модель список типов, размеров и роста рабочей одежды.
     */
    @GetMapping("/create")
    public String createWorkWear(WorkWearArrival workWearArrival, Model model) {
        List<String> workWearTypeList = WorkWearType.getValuesString();
        model.addAttribute("workWearTypeList", workWearTypeList);
        List<WorkWearSize> workWearSizeList = WorkWearSize.getValues();
        model.addAttribute("workWearSizeList", workWearSizeList);
        List<WorkWearHeight> workWearHeightList = WorkWearHeight.getValues();
        model.addAttribute("workWearHeightList", workWearHeightList);
        return "workwear_create";
    }

    /**
     Метод сохраняет новую рабочую одежду.
     Добавляет в модель список типов, размеров и роста рабочей одежды.
     */
    @PostMapping("/create")
    public String saveWorkWear(WorkWearArrival workWearArrival, Model model) {
        String message = workWearController.saveWorkWear(workWearArrival);
        model.addAttribute("message", message);
        List<String> workWearTypeList = WorkWearType.getValuesString();
        model.addAttribute("workWearTypeList", workWearTypeList);
        List<WorkWearSize> workWearSizeList = WorkWearSize.getValues();
        model.addAttribute("workWearSizeList", workWearSizeList);
        List<WorkWearHeight> workWearHeightList = WorkWearHeight.getValues();
        model.addAttribute("workWearHeightList", workWearHeightList);
        return "workwear_create";
    }

    /**
     Метод отображает страницу поиска рабочей одежды.
     */
    @GetMapping("/search")
    public String search() {
        return "workwear_search";
    }

    /**
     Метод отображает форму для поиска рабочей одежды по параметрам.
     Добавляет в модель список типов, размеров и роста рабочей одежды.
     */
    @GetMapping("/search/param")
    public String searchParam(Model model) {
        List<String> workWearTypeList = WorkWearType.getValuesString();
        model.addAttribute("workWearTypeList", workWearTypeList);
        List<String> workWearSizeList = WorkWearSize.getValuesString();
        model.addAttribute("workWearSizeList", workWearSizeList);
        return "workwear_search_param";
    }

    /**
     Метод отображает список всей доступной рабочей одежды.
     */
    @GetMapping("/search/all")
    public String searchAll(Model model) {
        model.addAttribute("status", "all");
        List<WorkWear> workWearList = workWearService.sortedWorkWearNotIssue(workWearController.findAllWorkWear());
        List<WorkWearView> workWearViewList = workWearService.createWorkWearView(workWearList);
        model.addAttribute("workWearViewList", workWearViewList);
        return "workwear_list";
    }

    /**
     Метод отображает список рабочей одежды определенного типа.
     */
    @GetMapping("/search/type")
    public String searchType(@RequestParam String workWearType, Model model) {
        WorkWearType workWearTyp = WorkWearType.getType(workWearType);
        List<WorkWear> workWearList = workWearService.sortedWorkWearNotIssue(workWearController.findAllWorkWearByWorkWearType(workWearTyp));
        List<WorkWearView> workWearViewList = workWearService.createWorkWearView(workWearList);
        model.addAttribute("workWearViewList", workWearViewList);
        return "workwear_list";
    }

    /**
     Метод отображает список рабочей одежды определенного размера.
     */
    @GetMapping("/search/size")
    public String searchSize(String workWearSize, Model model) {
        WorkWearSize wearSize = WorkWearSize.getType(workWearSize);
        List<WorkWear> workWearList = workWearService.sortedWorkWearNotIssue(workWearController.findAllWorkWearByWorkWearSize(wearSize));
        List<WorkWearView> workWearViewList = workWearService.createWorkWearView(workWearList);
        model.addAttribute("workWearViewList", workWearViewList);
        return "workwear_list";
    }

    /**
     Метод отображает форму для обновления рабочей одежды.
     Добавляет в модель список типов, размеров и роста рабочей одежды.
     */
    @GetMapping("/workwear_update/{id}")
    public String updateWorkWear(WorkWear workWear, Long id, Model model) {
        List<WorkWearType> workWearTypeList = WorkWearType.getValues();
        model.addAttribute("workWearTypeList", workWearTypeList);
        List<WorkWearSize> workWearSizeList = WorkWearSize.getValues();
        model.addAttribute("workWearSizeList", workWearSizeList);
        List<WorkWearHeight> workWearHeightList = WorkWearHeight.getValues();
        model.addAttribute("workWearHeightList", workWearHeightList);
        return "workwear_update";
    }

    /**
     Метод обновляет существующую рабочую одежду.
     Перенаправляет на страницу поиска рабочей одежды по типу.
     */
    @PostMapping("/update")
    public String update(WorkWear workWear) {
        workWearController.saveWorkWear(workWear);
        String path = URLEncoder.encode(workWear.getWorkWearType().getValue(), StandardCharsets.UTF_8);
        return "redirect:/workwear/search/type?workWearType=" + path;
    }

    /**
     Метод удаляет рабочую одежду по идентификатору.
     Перенаправляет на страницу поиска рабочей одежды по типу.
     */
    @GetMapping("/workwear_delete/{id}")
    public String deleteWorkWear(@PathVariable Long id) {
        String type = workWearController.findById(id).getWorkWearType().getValue();
        String path = URLEncoder.encode(type, StandardCharsets.UTF_8);
        workWearController.deleteWorkWear(id);
        return "redirect:/workwear/search/type?workWearType=" + path;
    }
}
