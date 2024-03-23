package workwear.workwearclient.clientControllerWeb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import workwear.workwearclient.controller.WorkShoesController;
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
        List<Integer> workShoesSizeList = new ArrayList<>();
        for (int i = 34; i <49 ; i++) {
            workShoesSizeList.add(i);
        }
        model.addAttribute("workShoesSizeList", workShoesSizeList);
        return "workshoes_create";
    }

    @PostMapping("/create")
    public String saveWorkWear(WorkShoesArrival workShoesArrival, Model model) {
        String message = workShoesController.saveWorkShoes(workShoesArrival);
        model.addAttribute("message", message);
        List<String> workShoesTypeList = WorkShoesType.getValuesString();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        List<Integer> workShoesSizeList = new ArrayList<>();
        for (int i = 34; i <49 ; i++) {
            workShoesSizeList.add(i);
        }
        model.addAttribute("workShoesSizeList", workShoesSizeList);

        return "workshoes_create";
    }

    @GetMapping("/search")
    public String searchShoes(){
        return "workshoes_search";
    }

    @GetMapping("/search/all")
    public String searchShoesAll(Model model){
        List<WorkShoesView> workShoesViewList = workShoesService.createShoesView(workShoesController.findAllWorkShoes());
        model.addAttribute("workShoesViewList", workShoesViewList);
        return "workshoes_list";
    }
}
