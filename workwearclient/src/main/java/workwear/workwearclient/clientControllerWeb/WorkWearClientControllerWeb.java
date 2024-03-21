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

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/workwear")
public class WorkWearClientControllerWeb {

    private  final WorkWearController workWearController;


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
    public String saveWorkWear (WorkWearArrival workWearArrival,Model model){
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
}
