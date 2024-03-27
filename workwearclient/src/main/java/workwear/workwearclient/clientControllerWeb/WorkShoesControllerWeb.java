package workwear.workwearclient.clientControllerWeb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import workwear.workwearclient.controller.WorkShoesController;
import workwear.workwearclient.model.WorkShoes;
import workwear.workwearclient.model.modelEnum.WorkShoesType;
import workwear.workwearclient.model.modelview.WorkShoesArrival;
import workwear.workwearclient.model.modelview.WorkShoesView;
import workwear.workwearclient.service.WorkShoesService;


import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/workshoes")
public class WorkShoesControllerWeb {

    private final WorkShoesController workShoesController;
    private final WorkShoesService workShoesService;

    @GetMapping("/create")
    public String createWorkShoes(WorkShoesArrival workShoesArrival, Model model) {
        List<String> workShoesTypeList = WorkShoesType.getValuesString();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        List<Integer> workShoesSizeList = workShoesService.createSizeList();
        model.addAttribute("workShoesSizeList", workShoesSizeList);
        return "workshoes_create";
    }

    @PostMapping("/create")
    public String saveWorkShoes(WorkShoesArrival workShoesArrival, Model model) {
        String message = workShoesController.saveWorkShoes(workShoesArrival);
        model.addAttribute("message", message);
        List<String> workShoesTypeList = WorkShoesType.getValuesString();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        List<Integer> workShoesSizeList = workShoesService.createSizeList();
        model.addAttribute("workShoesSizeList", workShoesSizeList);

        return "workshoes_create";
    }

    @GetMapping("/search")
    public String searchShoes() {
        return "workshoes_search";
    }

    @GetMapping("/search/all")
    public String searchShoesAll(Model model) {
        List<WorkShoes> workShoesList = workShoesService.sortedWorkShoesNotIssue(workShoesController.findAllWorkShoes());
        List<WorkShoesView> workShoesViewList = workShoesService.createShoesView(workShoesList);
        model.addAttribute("workShoesViewList", workShoesViewList);
        return "workshoes_list";
    }

    @GetMapping("/search/param")
    public String searchParam(Model model) {
        List<String> workShoesTypeList = WorkShoesType.getValuesString();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        List<Integer> workShoesSizeList = workShoesService.createSizeList();
        model.addAttribute("workShoesSizeList", workShoesSizeList);
        return "workshoes_search_param";
    }

    @GetMapping("/search/type")
    public String searchType(String workShoesType, Model model) {
        WorkShoesType workShoesType1 = WorkShoesType.getType(workShoesType);
        List<WorkShoes> workShoesList = workShoesService.sortedWorkShoesNotIssue(workShoesController
                .findAllWorkShoesByWorkShoesType(workShoesType1));
        List<WorkShoesView> workShoesViewList = workShoesService.createShoesView(workShoesList);
        model.addAttribute("workShoesViewList", workShoesViewList);
        return "workshoes_list";
    }

    @GetMapping("/search/size")
    public String searchSize(Integer workShoesSize, Model model) {
        List<WorkShoes> workShoesList = workShoesService.sortedWorkShoesNotIssue(workShoesController
                .findAllWorkShoesByWorkShoesSize(workShoesSize));
        List<WorkShoesView> workShoesViewList = workShoesService.createShoesView(workShoesList);
        model.addAttribute("workShoesViewList", workShoesViewList);
        return "workshoes_list";
    }
    @GetMapping("/workshoes_delete/{id}")
    public String deleteWorkShoesById(@PathVariable Long id){
        workShoesController.deleteWorkShoes(id);
        return "redirect:/workshoes/search";
    }

    @GetMapping ("/workshoes_update/{id}")
    public String updateWorkShoes (WorkShoes workShoes, Long id, Model model){
        List<WorkShoesType> workShoesTypeList = WorkShoesType.getValues();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        List<Integer> workShoesSizeList = workShoesService.createSizeList();
        model.addAttribute("workShoesSizeList", workShoesSizeList);
        return "workshoes_update";
    }

    @PostMapping("/update/shoes")
    public String updateWorkShoes(WorkShoes workShoes, Model model){
        List<WorkShoes> workShoesList = new ArrayList<>();
        workShoesList.add(workShoes);
        List<WorkShoesView> workShoesViewList = workShoesService.createShoesView(workShoesList);
        model.addAttribute("workShoesViewList", workShoesViewList);
        workShoesController.saveWorkShoes(workShoes);
        return "workshoes_list";
    }
}
