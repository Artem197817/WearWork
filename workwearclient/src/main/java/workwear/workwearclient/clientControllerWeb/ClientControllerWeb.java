package workwear.workwearclient.clientControllerWeb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ClientControllerWeb {

    @GetMapping
    public String init() {
        return "index";
    }

    @GetMapping("employee")
    public String employee() {
        return "employee";
    }

    @GetMapping("workwear")
    public String workwear() {
        return "workwear";
    }
}
