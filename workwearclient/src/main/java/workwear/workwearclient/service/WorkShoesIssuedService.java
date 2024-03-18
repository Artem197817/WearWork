package workwear.workwearclient.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import workwear.workwearclient.controller.EmployeeController;
import workwear.workwearclient.controller.WorkShoesController;
import workwear.workwearclient.model.Employee;
import workwear.workwearclient.model.WorkShoes;
import workwear.workwearclient.model.WorkShoesIssued;
import workwear.workwearclient.model.WorkShoesIssuedView;
import workwear.workwearclient.view.input.InputValue;
import workwear.workwearclient.view.output.Output;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class WorkShoesIssuedService {

    private final EmployeeController employeeController;
    private final InputValue inputValue;
    private final Output output;
    private final WorkShoesController workShoesController;
    private final WorkShoesService workShoesService;



    public WorkShoesIssued issuedWorkShoes() {
        Employee employee = employeeController.findEmployee();
        if (employee == null) {
            output.output("Ошибка при выборе сотрудникв");
            return null;
        }
        List<WorkShoes> workShoesList = workShoesController.findAllWorkShoesByWorkShoesType();
        if (workShoesList.isEmpty()) return null;
        int size = inputValue.inputInt("Введите требуемый размер обуви");
        List<WorkShoes> workShoesListSortedBySize = workShoesService.sortedWorkShoesNotIssue(workShoesList).stream()
                .filter(workShoes -> size == workShoes.getWorkShoesSize())
                .toList();
        output.outputList(workShoesListSortedBySize);
        Long idWorkShoes = inputValue.inputLong("Введите id выдаваемой обуви ");
        WorkShoes workShoes = workShoesListSortedBySize.stream()
                .filter(workShoes1 -> workShoes1.getId().equals(idWorkShoes))
                .findFirst().orElse(null);
        if (workShoes == null) {
            output.output("Ошибка при определении спецобуви");
            return null;
        }
        Integer monthPeriod = inputValue.inputInt("Введите срок выдачи спецобуви в месяцах");
        return new WorkShoesIssued(employee.getId(), idWorkShoes, monthPeriod);
    }

    public List<WorkShoesIssuedView> createWorkShoesIssuedView (Map<WorkShoesIssued,WorkShoes> workShoesMap){
        List<WorkShoesIssuedView> workShoesIssuedViewList = new ArrayList<>();
        for(Map.Entry<WorkShoesIssued,WorkShoes> entry:workShoesMap.entrySet()){
            workShoesIssuedViewList.add(new WorkShoesIssuedView(entry.getKey(),entry.getValue()));
        }
        return workShoesIssuedViewList;
    }
}


