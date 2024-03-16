package workwear.workwearclient.view.output;



import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Output {


    public <E> void outputList (List<E> list){
        list.forEach(System.out::println);
    }


    public  void output(String message) {
        System.out.println(message);
    }
}
