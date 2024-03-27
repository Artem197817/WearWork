package workwear.workwearclient.clientControllerWeb;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import workwear.workwearclient.controller.WorkShoesController;
import workwear.workwearclient.controller.WorkShoesIssuedController;
import workwear.workwearclient.controller.WorkWearController;
import workwear.workwearclient.controller.WorkWearIssuedController;
import workwear.workwearclient.model.WorkShoes;
import workwear.workwearclient.model.WorkShoesIssued;
import workwear.workwearclient.model.WorkWear;
import workwear.workwearclient.model.WorkWearIssued;
import workwear.workwearclient.model.modelEnum.WorkShoesType;
import workwear.workwearclient.model.modelEnum.WorkWearType;
import workwear.workwearclient.model.modelview.ShoesIssuedView;
import workwear.workwearclient.model.modelview.WearIssuedView;
import workwear.workwearclient.model.modelview.WorkShoesView;
import workwear.workwearclient.model.modelview.WorkWearView;
import workwear.workwearclient.service.WorkShoesIssuedService;
import workwear.workwearclient.service.WorkShoesService;
import workwear.workwearclient.service.WorkWearIssueService;
import workwear.workwearclient.service.WorkWearService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/issue")
public class IssueControllerWeb {

    private final WorkWearService workWearService;
    private final WorkWearController workWearController;
    private final WorkWearIssueService workWearIssueService;
    private final WorkWearIssuedController workWearIssuedController;
    private final WorkShoesService workShoesService;
    private final WorkShoesIssuedService workShoesIssuedService;
    private final WorkShoesController workShoesController;
    private final WorkShoesIssuedController workShoesIssuedController;

    @GetMapping("/issue")
    public String issue(ModelAndView modelAndView) {
        var id = modelAndView.getModelMap().getAttribute("id");
        return "redirect:/issue";
    }

    @GetMapping("/wear")
    public String issueWear(@RequestParam Long id, Model model) {
        List<String> workWearTypeList = WorkWearType.getValuesString();
        model.addAttribute("workWearTypeList", workWearTypeList);
        model.addAttribute("id", id);
        return "issue_wear_type";
    }

    @GetMapping("/wear/type")
    public String issueListWear(@RequestParam Long id, @RequestParam String workWearType, Model model) {
        model.addAttribute("id", id);
        WorkWearType workWearTyp = WorkWearType.getType(workWearType);
        List<WorkWear> workWearList = workWearService.sortedWorkWearNotIssue(workWearController
                .findAllWorkWearByWorkWearType(workWearTyp));
        List<WorkWearView> workWearViewList = workWearService.createWorkWearView(workWearList);
        model.addAttribute("workWearViewList", workWearViewList);
        return "issue_wear_list";
    }

    @GetMapping("/wear/{wearid}/{id}")
    public String issueWearFinal(WearIssuedView wearIssuedView, @PathVariable Long wearid, @PathVariable Long id, Model model) {
        wearIssuedView.setEmployeeId(id);
        wearIssuedView.setWorkWearId(wearid);
        model.addAttribute("employeeId", id);
        model.addAttribute("workWearId", wearid);
        return "issued_create_wear";
    }

    @PostMapping("/wear/create")
    public String issueWearCreate(WearIssuedView wearIssuedView) {
        if (wearIssuedView.getMonthPeriod() < 1) {
            return "redirect:/issue/wear/" + wearIssuedView.getWorkWearId() + wearIssuedView.getEmployeeId();
        }
        WorkWearIssued workWearIssued = workWearIssueService.createWorkWearIssued(wearIssuedView);
        workWearIssuedController.saveWorkWearIssued(workWearIssued);
        return "redirect:/employee/employee_issue/" + wearIssuedView.getEmployeeId();
    }


    @GetMapping("/shoes")
    public String issueShoes(@RequestParam Long id, Model model) {
        List<String> workShoesTypeList = WorkShoesType.getValuesString();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        model.addAttribute("id", id);
        return "issue_shoes_type";
    }

    @GetMapping("/shoes/type")
    public String issueListShoes(@RequestParam Long id, @RequestParam String workShoesType, Model model) {
        model.addAttribute("id", id);
         WorkShoesType workShoesType1 = WorkShoesType.getType(workShoesType);
         List<WorkShoes> workShoesList = workShoesService.sortedWorkShoesNotIssue(workShoesController
                 .findAllWorkShoesByWorkShoesType(workShoesType1));
      List<WorkShoesView>  workShoesViewList = workShoesService.createShoesView(workShoesList);
        model.addAttribute("workShoesViewList", workShoesViewList);
        return "issue_shoes_list";
    }

    @GetMapping("/shoes/{shoesId}/{id}")
    public String issueShoesFinal(ShoesIssuedView shoesIssuedView, @PathVariable Long shoesId, @PathVariable Long id, Model model) {
        shoesIssuedView.setEmployeeId(id);
        shoesIssuedView.setWorkShoesId(shoesId);
        model.addAttribute("employeeId", id);
        model.addAttribute("workShoesId", shoesId);
        return "issued_create_shoes";
    }

    @PostMapping("/shoes/create")
    public String issueShoesCreate(ShoesIssuedView shoesIssuedView) {
        if (shoesIssuedView.getMonthPeriod() < 1) {
            return "redirect:/issue/shoes/" + shoesIssuedView.getWorkShoesId() + shoesIssuedView.getEmployeeId();
        }
        WorkShoesIssued workShoesIssued = workShoesIssuedService.createWorkShoesIssue(shoesIssuedView);
      workShoesIssuedController.saveWorkShoesIssued(workShoesIssued);
        return "redirect:/employee/employee_issue/" + shoesIssuedView.getEmployeeId();
    }
}
