package workwear.workwearclient.clientControllerWeb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import workwear.workwearclient.controller.WorkShoesTotalController;
import workwear.workwearclient.controller.WorkWearTotalController;
import workwear.workwearclient.model.WorkShoesTotal;
import workwear.workwearclient.model.modelview.WorkShoesTotalView;
import workwear.workwearclient.model.modelview.WorkWearTotalView;
import workwear.workwearclient.service.WorkWearReserveService;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/reserve")
public class WorkWearReserveWeb {

   private final WorkShoesTotalController workShoesTotalController;
   private final WorkWearTotalController workWearTotalController;
   private final WorkWearReserveService workWearReserveService;

   @GetMapping
   public String reserve(){
       return "reserve";
   }

   @GetMapping("/workwear")
   public String reserveWear(){
      return "reserve_wear";
   }

   @GetMapping("/workshoes")
   public String reserveShoes(){
      return "reserve_shoes";
   }

   @GetMapping("/workwear/all")
   public String reserveWearAll(Model model){
      List<WorkWearTotalView> workWearTotalViewList = workWearReserveService.createWorWearTotalView(workWearTotalController
              .findAllWorkWearSortedNumber());
      model.addAttribute("workWearTotalViewList", workWearTotalViewList);
      return "reserve_wear_list";
   }

   @GetMapping("/workshoes/all")
   public String reserveShoesAll(Model model){
      List<WorkShoesTotalView> workShoesTotalViewList = workWearReserveService.createWorkShoesTotalView((workShoesTotalController
              .findAllWorkShoesSortedNumber()));
      model.addAttribute("workShoesTotalViewList", workShoesTotalViewList);
      return "reserve_shoes_list";
   }
}
