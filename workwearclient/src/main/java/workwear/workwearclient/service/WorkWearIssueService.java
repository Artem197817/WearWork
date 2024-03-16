package workwear.workwearclient.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import workwear.workwearclient.controller.EmployeeController;
import workwear.workwearclient.controller.WorkWearController;
import workwear.workwearclient.model.Employee;
import workwear.workwearclient.model.WorkWear;
import workwear.workwearclient.model.WorkWearIssued;
import workwear.workwearclient.model.modelEnum.WorkWearSize;
import workwear.workwearclient.view.input.InputValue;
import workwear.workwearclient.view.output.Output;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkWearIssueService {

    private final EmployeeController employeeController;
    private final InputValue inputValue;
    private final Output output;
    private final WorkWearController workWearController;
    private final WorkWearService workWearService;

    public WorkWearIssued issuedWorkWear() {
        Employee employee = employeeController.findEmployee();
        if (employee == null) {
            output.output("Ошибка при выборе сотрудникв");
            return null;
        }
        List<WorkWear> workWearList = workWearController.findAllWorkWearByWorkWearType();
        if (workWearList.isEmpty()) return null;
        String size = inputValue.inputEnum("Введите требуемый размер одежды", WorkWearSize.class);
        WorkWearSize workWearSize = WorkWearSize.getType(size);
        List<WorkWear> workWearListSortedBySize = workWearService.sortedWorkWearNotIssue(workWearList).stream()
                .filter(workWear -> workWear.getWorkWearSize().equals(workWearSize))
                .toList();
        output.outputList(workWearListSortedBySize);
        Long idWorkWear = inputValue.inputLong("Введите id выдаваемой одежды ");
        WorkWear workWear = workWearListSortedBySize.stream()
                .filter(workWear1 -> workWear1.getId().equals(idWorkWear))
                .findFirst().orElse(null);
        if (workWear == null) {
            output.output("Ошибка при определении спецодежды");
            return null;
        }
        Integer wearPeriod = inputValue.inputInt("Введите срок выдачи спецодежды в месяцах");
        return new WorkWearIssued(employee.getId(), idWorkWear,wearPeriod);
    }

}
