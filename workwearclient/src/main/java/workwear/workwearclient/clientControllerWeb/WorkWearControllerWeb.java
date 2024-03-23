package workwear.workwearclient.clientControllerWeb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import workwear.workwearclient.controller.WorkWearController;
import workwear.workwearclient.model.modelEnum.*;
import workwear.workwearclient.model.modelview.WorkWearArrival;
import workwear.workwearclient.model.modelview.WorkWearView;
import workwear.workwearclient.service.WorkWearService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/workwear")
public class WorkWearControllerWeb {

    private final WorkWearController workWearController;
    private final WorkWearService workWearService;

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

    @GetMapping("/search")
    public String search() {
        return "workwear_search";
    }

    @GetMapping("/search/param")
    public String searchParam(Model model) {
        List<String> workWearTypeList = WorkWearType.getValuesString();
        model.addAttribute("workWearTypeList", workWearTypeList);
        List<String> workWearSizeList = WorkWearSize.getValuesString();
        model.addAttribute("workWearSizeList", workWearSizeList);
        return "workwear_search_param";
    }

    @GetMapping("/search/all")
    public String searchAll(Model model) {
        model.addAttribute("status", "all");
        List<WorkWearView> workWearViewList = workWearService.createWorkWearView(workWearController.findAllWorkWear());
        model.addAttribute("workWearViewList", workWearViewList);
        return "workwear_list";
    }

    @GetMapping("/search/type")
    public String searchType(String workWearType, Model model) {
        WorkWearType workWearTyp = WorkWearType.getType(workWearType);
        List<WorkWearView> workWearViewList = workWearService.createWorkWearView(workWearController
                .findAllWorkWearByWorkWearType(workWearTyp));
        model.addAttribute("workWearViewList", workWearViewList);
        return "workwear_list";
    }
    @GetMapping("/search/size")
    public String searchSize(String workWearSize, Model model){
        WorkWearSize wearSize = WorkWearSize.getType(workWearSize);
        List<WorkWearView> workWearViewList = workWearService.createWorkWearView(workWearController
                .findAllWorkWearByWorkWearSize(wearSize));
        model.addAttribute("workWearViewList",workWearViewList);
        return "workwear_list";
    }
}
