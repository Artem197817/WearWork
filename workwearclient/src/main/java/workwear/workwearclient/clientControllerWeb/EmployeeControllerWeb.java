package workwear.workwearclient.clientControllerWeb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import workwear.workwearclient.controller.EmployeeController;
import workwear.workwearclient.controller.WorkShoesIssuedController;
import workwear.workwearclient.controller.WorkWearIssuedController;
import workwear.workwearclient.model.Employee;
import workwear.workwearclient.model.modelEnum.Company;
import workwear.workwearclient.model.modelEnum.ProductionDivision;
import workwear.workwearclient.model.modelview.WorkShoesIssuedView;
import workwear.workwearclient.model.modelview.WorkWearIssuedView;
import workwear.workwearclient.service.WorkShoesIssuedService;
import workwear.workwearclient.service.WorkWearIssueService;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeControllerWeb {

    private  final  EmployeeController employeeController;
    private final WorkShoesIssuedController workShoesIssuedController;
    private final WorkWearIssuedController workWearIssuedController;
    private final WorkWearIssueService workWearIssueService;
    private final WorkShoesIssuedService workShoesIssuedService;

    @GetMapping("/create")
    public String populateList(Employee  employee,Model model) {
        List<ProductionDivision> productionDivisions = ProductionDivision.getValues();
        model.addAttribute("productionDivisions", productionDivisions);
        List<Company> companies = Company.getValues();
        model.addAttribute("companies",companies);
        return "employee_create";
    }

    @PostMapping("/create")
    public String saveEmployee(Employee employee){
        employeeController.saveEmployee(employee);
        return "redirect:/employee/create";
    }

    @GetMapping("/search")
    public String searchEmployee(){
        return "employee_search";
    }
    @GetMapping("/search/all")
    public  String allEmployee(Model model){
        List<Employee> employees = employeeController.findAllEmployee();
        model.addAttribute("employees",employees);
        model.addAttribute("message", "Все сотрудники");
        return "employee_list";
    }
    @GetMapping(value = "search/employee_update/{id}")
    public String updateUserForm(Employee employee, Model model) {
        List<ProductionDivision> productionDivisions = ProductionDivision.getValues();
        model.addAttribute("productionDivisions", productionDivisions);
        List<Company> companies = Company.getValues();
        model.addAttribute("companies",companies);
        return "employee_update";
    }

    @PostMapping("/employee_update")
    public String updateUser(Employee employee) {// return logics add
        employeeController.saveEmployee(employee);
        return "redirect:/employee/search/all";
    }

    @GetMapping("/search/employee_delete/{id}")
    public String deleteEmployee(@PathVariable Long id, Model model) {// return logics add
        if (workWearIssuedController.findWorkWearIssuedEmployee(id).isEmpty()
                && workShoesIssuedController.findWorkShoesIssuedEmployee(id).isEmpty()) {
            employeeController.deleteEmployeeById(id);
        }else{
            model.addAttribute("message", "Необходимо списать одежду");
        }
        return "redirect:/employee/search/all";
    }

    @GetMapping("/search/employee_issue/{id}")
    public String employeeIssued(@PathVariable Long id, Model model){
        List<WorkWearIssuedView> workWearIssuedViewList = workWearIssuedController.findWorkWearIssuedEmployee(id);
        List<WorkShoesIssuedView> workShoesIssuedViewList = workShoesIssuedController.findWorkShoesIssuedEmployee(id);
        Employee employee = employeeController.findById(id);
        String message = employee.getLastName() + " "
                + employee.getFirstName() + " "
                + employee.getProductionDivision().getValue();
        model.addAttribute("message", message);
        model.addAttribute("wears",workWearIssuedViewList);
        model.addAttribute("shoesList",workShoesIssuedViewList);
         return "employee_issue";
    }


}
