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


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/workshoes")
public class WorkShoesControllerWeb {

    private final WorkShoesController workShoesController;
    private final WorkShoesService workShoesService;

    /**
     Метод отображает форму для создания новой рабочей обуви.
     Добавляет в модель список типов и размеров рабочей обуви.
     */
    @GetMapping("/create")
    public String createWorkShoes(WorkShoesArrival workShoesArrival, Model model) {
        List<String> workShoesTypeList = WorkShoesType.getValuesString();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        List<Integer> workShoesSizeList = workShoesService.createSizeList();
        model.addAttribute("workShoesSizeList", workShoesSizeList);
        return "workshoes_create";
    }

    /**
     Метод сохраняет новую рабочую обувь.
     Добавляет в модель список типов и размеров рабочей обуви.
     */
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

    /**
     Метод отображает страницу поиска рабочей обуви.
     */
    @GetMapping("/search")
    public String searchShoes() {
        return "workshoes_search";
    }

    /**
     Метод отображает список всей доступной рабочей обуви.
     */
    @GetMapping("/search/all")
    public String searchShoesAll(Model model) {
        List<WorkShoes> workShoesList = workShoesService.sortedWorkShoesNotIssue(workShoesController.findAllWorkShoes());
        List<WorkShoesView> workShoesViewList = workShoesService.createShoesView(workShoesList);
        model.addAttribute("workShoesViewList", workShoesViewList);
        return "workshoes_list";
    }

    /**
     Метод отображает форму для поиска рабочей обуви по параметрам.
     Добавляет в модель список типов и размеров рабочей обуви.
     */
    @GetMapping("/search/param")
    public String searchParam(Model model) {
        List<String> workShoesTypeList = WorkShoesType.getValuesString();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        List<Integer> workShoesSizeList = workShoesService.createSizeList();
        model.addAttribute("workShoesSizeList", workShoesSizeList);
        return "workshoes_search_param";
    }

    /**
     Метод отображает список рабочей обуви определенного типа.
     */
    @GetMapping("/search/type")
    public String searchType(String workShoesType, Model model) {
        WorkShoesType workShoesType1 = WorkShoesType.getType(workShoesType);
        List<WorkShoes> workShoesList = workShoesService.sortedWorkShoesNotIssue(workShoesController
                .findAllWorkShoesByWorkShoesType(workShoesType1));
        List<WorkShoesView> workShoesViewList = workShoesService.createShoesView(workShoesList);
        model.addAttribute("workShoesViewList", workShoesViewList);
        return "workshoes_list";
    }

    /**
     Метод отображает список рабочей обуви определенного размера.
     */
    @GetMapping("/search/size")
    public String searchSize(Integer workShoesSize, Model model) {
        List<WorkShoes> workShoesList = workShoesService.sortedWorkShoesNotIssue(workShoesController
                .findAllWorkShoesByWorkShoesSize(workShoesSize));
        List<WorkShoesView> workShoesViewList = workShoesService.createShoesView(workShoesList);
        model.addAttribute("workShoesViewList", workShoesViewList);
        return "workshoes_list";
    }

    /**
     Метод удаляет рабочую обувь по идентификатору.
     */
    @GetMapping("/workshoes_delete/{id}")
    public String deleteWorkShoesById(@PathVariable Long id) {
        String type = workShoesController.findById(id).getWorkShoesType().getValue();
        String path = URLEncoder.encode(type, StandardCharsets.UTF_8);
        workShoesController.deleteWorkShoes(id);
        return "redirect:/workshoes/search/type?workShoesType=" + path;
    }

    /**
     Метод отображает форму для обновления рабочей обуви.
     Добавляет в модель список типов и размеров рабочей обуви.
     */
    @GetMapping("/workshoes_update/{id}")
    public String updateWorkShoes(WorkShoes workShoes, Long id, Model model) {
        List<WorkShoesType> workShoesTypeList = WorkShoesType.getValues();
        model.addAttribute("workShoesTypeList", workShoesTypeList);
        List<Integer> workShoesSizeList = workShoesService.createSizeList();
        model.addAttribute("workShoesSizeList", workShoesSizeList);
        return "workshoes_update";
    }

    /**
     Метод обновляет существующую рабочую обувь.
     */
    @PostMapping("/update/shoes")
    public String updateWorkShoes(WorkShoes workShoes, Model model) {
        String path = URLEncoder.encode(workShoes.getWorkShoesType().getValue(), StandardCharsets.UTF_8);
        workShoesController.saveWorkShoes(workShoes);
        return "redirect:/workshoes/search/type?workShoesType=" + path;
    }
}
