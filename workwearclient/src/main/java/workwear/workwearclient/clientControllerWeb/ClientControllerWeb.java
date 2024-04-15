package workwear.workwearclient.clientControllerWeb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ClientControllerWeb {

    /**
     метод отвечает за первоначальную загрузку страницы при запуске приложения.
     */
    @GetMapping
    public String init() {
        return "index";
    }

    /**
     метод отвечает за загрузку страницы сотрудников.
     */
    @GetMapping("employee")
    public String employee() {
        return "employee";
    }

    /**
     метод отвечает за загрузку страницы рабочей одежды.
     */
    @GetMapping("workwear")
    public String workWear() {
        return "workwear";
    }

    /**
     метод отвечает за загрузку страницы рабочей обуви.
     */
    @GetMapping("workshoes")
    public String workShoes() {
        return "workshoes";
    }
}
