package broker;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class BrokerController {

    @RequestMapping("/")
    public String index() {
        return "Greetings";
    }

}
