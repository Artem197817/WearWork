package workwear.workwearclient.clientControllerWeb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import workwear.workwearclient.controller.EmployeeController;
import workwear.workwearclient.controller.WorkShoesIssuedController;
import workwear.workwearclient.controller.WorkWearIssuedController;
import workwear.workwearclient.model.Employee;
import workwear.workwearclient.model.WorkShoesIssued;
import workwear.workwearclient.model.WorkWearIssued;
import workwear.workwearclient.model.modelEnum.Company;
import workwear.workwearclient.model.modelEnum.ProductionDivision;
import workwear.workwearclient.model.modelview.EmployeeView;
import workwear.workwearclient.model.modelview.WorkShoesIssuedView;
import workwear.workwearclient.model.modelview.WorkWearIssuedView;
import workwear.workwearclient.service.EmployeeService;


import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeControllerWeb {

    private final EmployeeController employeeController;
    private final WorkShoesIssuedController workShoesIssuedController;
    private final WorkWearIssuedController workWearIssuedController;
    private final EmployeeService employeeService;
    private final IssueControllerWeb issueControllerWeb;

    /**
     Метод, возвращающий страницу создания сотрудника. Добавляет в модель список производственных подразделений и компаний.
     */
    @GetMapping("/create")
    public String populateList(Employee employee, Model model) {
        List<ProductionDivision> productionDivisions = ProductionDivision.getValues();
        model.addAttribute("productionDivisions", productionDivisions);
        List<Company> companies = Company.getValues();
        model.addAttribute("companies", companies);
        return "employee_create";
    }

    /**
     Метод, сохраняющий сотрудника в базе данных.
     */
    @PostMapping("/create")
    public String saveEmployee(Employee employee) {
        employeeController.saveEmployee(employee);
        return "redirect:/employee/create";
    }

    /**
     Метод, возвращающий страницу поиска сотрудника
     */
    @GetMapping("/search")
    public String searchEmployee() {
        return "employee_search";
    }

    /**
     Метод, возвращающий список всех сотрудников в виде EmployeeView
     */
    @GetMapping("/search/all")
    public String allEmployee(Model model) {
        List<EmployeeView> employees = employeeService.createEmployeeView(employeeController.findAllEmployee());
        model.addAttribute("employees", employees);
        model.addAttribute("message", "Все сотрудники");
        return "employee_list";
    }

    /**
     Метод, возвращающий страницу обновления данных сотрудника.
     Добавляет в модель список производственных подразделений и компаний.
     */
    @GetMapping(value = "/employee_update/{id}")
    public String updateUserForm(Employee employee, Model model) {
        List<ProductionDivision> productionDivisions = ProductionDivision.getValues();
        model.addAttribute("productionDivisions", productionDivisions);
        List<Company> companies = Company.getValues();
        model.addAttribute("companies", companies);
        return "employee_update";
    }

    /**
     Метод, обновляющий данные сотрудника в базе данных.
     */
    @PostMapping("/employee_update")
    public String updateUser(Employee employee) {// return logics add
        employeeController.saveEmployee(employee);
        return "redirect:/employee/search";
    }

    /**
     Метод, удаляющий сотрудника из базы данных, если у сотрудника нет выданной одежды или обуви.
     В противном случае добавляет в модель сообщение об ошибке.
     */
    @GetMapping("/employee_delete/{id}")
    public String deleteEmployee(@PathVariable Long id, Model model) {// return logics add
        if (workWearIssuedController.findWorkWearIssuedEmployee(id).isEmpty()
                && workShoesIssuedController.findWorkShoesIssuedEmployee(id).isEmpty()) {
            employeeController.deleteEmployeeById(id);
        } else {
            model.addAttribute("message", "Необходимо списать одежду");
        }
        return "redirect:/employee/search";
    }

    /**
     Метод, возвращающий страницу списка выданной одежды и обуви конкретному сотруднику.
     */
    @GetMapping("/employee_issue/{id}")
    public String employeeIssued(@PathVariable Long id, Model model) {
        List<WorkWearIssuedView> workWearIssuedViewList = workWearIssuedController.findWorkWearIssuedEmployee(id);
        List<WorkShoesIssuedView> workShoesIssuedViewList = workShoesIssuedController.findWorkShoesIssuedEmployee(id);
        Employee employee = employeeController.findById(id);
        String message = employee.getLastName() + " "
                + employee.getFirstName() + " "
                + employee.getProductionDivision().getValue();
        model.addAttribute("message", message);
        model.addAttribute("wears", workWearIssuedViewList);
        model.addAttribute("shoesList", workShoesIssuedViewList);
        return "employee_issue";
    }

    /**
     Метод, возвращающий страницу поиска сотрудника по параметрам.
     Добавляет в модель список производственных подразделений.
     */
    @GetMapping("/search/param")
    public String searchParam(Model model) {
        List<String> productionDivisions = ProductionDivision.getValuesString();
        model.addAttribute("productionDivisions", productionDivisions);
        return "employee_search_param";
    }

    /**
     Метод, ищущий сотрудников по фамилии.
     Если фамилия не указана, перенаправляет на страницу поиска по параметрам.
     */
    @GetMapping("/search/last_name")
    public String searchLastname(String lastName, Model model) {
        if (lastName == null || lastName.isEmpty()) return "redirect:/employee/search/param";
        List<EmployeeView> employeeViewList = employeeService.createEmployeeView(employeeController
                .findEmployeeByLastName(lastName));
        model.addAttribute("employees", employeeViewList);
        return "employee_list";
    }

    /**
     Метод, ищущий сотрудников по производственному подразделению.
     */
    @GetMapping("/search/division")
    public String searchByDivision(String productionDivision, Model model) {
        ProductionDivision productionDivisionEnum = ProductionDivision.getType(productionDivision);
        List<EmployeeView> employeeViewList = employeeService.createEmployeeView((employeeController
                .findAllEmployeeByProductionDivision(productionDivisionEnum)));
        model.addAttribute("employees", employeeViewList);
        return "employee_list";
    }

    /**
     Метод, возвращающий выданную одежду в хранилище.
     */
    @GetMapping("/return/{id}")
    public String returnStorageWear(@PathVariable Long id, Model model) {
        WorkWearIssued workWearIssued = workWearIssuedController.findWorkWearIssuedById(id);
        workWearIssuedController.returnWorkWearOnStorage(id);
        return "redirect:/employee/employee_issue/" + workWearIssued.getEmployeeId();
    }

    /**
     Метод, возвращающий выданную обувь в хранилище.
     */
    @GetMapping("/return_shoes/{id}")
    public String returnStorageShoes(@PathVariable Long id, Model model) {
        WorkShoesIssued workShoesIssued = workShoesIssuedController.findById(id);
        workShoesIssuedController.returnWorkShoesOnStorage(id);
        return "redirect:/employee/employee_issue/" + workShoesIssued.getEmployeeId();
    }

    /**
     Метод, удаляющий выданную одежду из базы данных.
     */
    @GetMapping("/delete/{id}")
    public String deleteEmployeeWear(@PathVariable Long id) {
        WorkWearIssued workWearIssued = workWearIssuedController.findWorkWearIssuedById(id);
        workWearIssuedController.deleteWorkWearIssued(id);
        return "redirect:/employee/employee_issue/" + workWearIssued.getEmployeeId();
    }

    /**
     Метод, удаляющий выданную обувь из базы данных.
     */
    @GetMapping("/delete_shoes/{id}")
    public String deleteEmployeeShoes(@PathVariable Long id) {
        WorkShoesIssued workShoesIssued = workShoesIssuedController.findById(id);
        workShoesIssuedController.deleteWorkShoesIssued(id);
        return "redirect:/employee/employee_issue/" + workShoesIssued.getEmployeeId();
    }

    /**
     Метод, возвращающий страницу выдачи одежды и обуви сотруднику.
     */
    @GetMapping("/issue/{id}")
    public ModelAndView issue(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("issue");
        modelAndView.getModelMap().addAttribute("id", id);
        issueControllerWeb.issue(modelAndView);
        return modelAndView;
    }
}
