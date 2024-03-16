package workwear.workwearclient.clientController;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import workwear.workwearclient.view.input.InputValue;


@AllArgsConstructor
@Component
public class ClientController implements CommandLineRunner{



   private final InputValue inputValue;
   private  final  EmployeeClientController employeeClientController;
   private final WorkShoesClientController workShoesClientController;
   private final WorkWearClientController workWearClientController;


    @Override
    public  void run(String...args) {

        boolean is = true;

        while(is){
            int key = inputValue.inputInt("Введите команду");
            switch (key) {
                case 1 -> help();
                case 2 -> employeeClientController.run();
                case 4 -> workShoesClientController.run();
                case 3 -> workWearClientController.run();
                case 0 -> is = false;
            }
        }
    }

    private void help() {

        System.out.println("1 -Помощь");
        System.out.println("2 - Сотрудники");
        System.out.println("3 - Спецодежда");
        System.out.println("4 - Спецобувь");
        System.out.println("0 - Выход");
    }


}
