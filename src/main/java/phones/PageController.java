package phones;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class PageController {

    @RequestMapping("/") //odpowiedz na żadanie GET o strone głowną
    @ResponseBody // to co zwraca metoda ma byc wyslane jako body strony html
    public String przywitaj() {
        return "Hello <b> Bartek </b>!";
    }

    @RequestMapping("/now")
    @ResponseBody
    public String pokazDate() {
        return LocalDateTime.now().toString();
    }
}