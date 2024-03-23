package workwear.workwearclient.clientController;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import workwear.workwearclient.controller.WorkShoesController;
import workwear.workwearclient.controller.WorkShoesIssuedController;
import workwear.workwearclient.controller.WorkShoesTotalController;
import workwear.workwearclient.view.input.InputValue;

@Component
@AllArgsConstructor
public class WorkShoesClientController {

    private final InputValue inputValue;
    private final WorkShoesIssuedController workShoesIssuedController;
    private final WorkShoesController workShoesController;
    private final WorkShoesTotalController workShoesTotalController;

    public void run(){

        boolean is = true;
        while(is){

            int key = inputValue.inputInt("Введите команду");
            switch (key) {
                case 1 -> help();
                case 2 -> workShoesController.saveNewWorkShoes();
                case 3 -> workShoesIssuedController.saveWorkShoesIssued();
                case 4 -> workShoesIssuedController.findAllWorkShoesIssued().forEach(System.out::println);
                case 5 -> workShoesIssuedController.findWorkShoesIssuedByEmployee().forEach(System.out::println);
                case 6 -> workShoesIssuedController.deleteWorkShoesIssued();
                case 7 -> workShoesTotalController.findAllWorkShoesSortedNumber().forEach(System.out::println);
                case 8 -> workShoesTotalController.findWorkShoesBySizeSortedNumber().forEach(System.out::println);
                case 9 -> workShoesTotalController.findWorkShoesByTypeSortedNumber().forEach(System.out::println);
                case 0 -> is = false;
            }
        }
    }

    private void help() {

        System.out.println("1 -Помощь");
        System.out.println("2 - Пополнения склада обуви");
        System.out.println("3 - Выдать спецобувь");
        System.out.println("4 - Список всей выданной обуви");
        System.out.println("5 - Список обуви выданной сотруднику");
        System.out.println("6 - Списать выданную обувь");
        System.out.println("7 - Список всей обуви ");
        System.out.println("8 - Список обуви пр размеру");
        System.out.println("9 - Список обуви по типу");
        System.out.println("0 - Выход");
    }
}
