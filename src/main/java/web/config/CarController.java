package web.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;

import java.util.Arrays;

@Controller
public class CarController {
    private final Car[] carArray = {
            new Car("Toyota", "Celica", 2000),
            new Car("Subaru", "BRZ", 2010),
            new Car("Tesla", "model S", 2020),
            new Car("Ford", "model T", 1910),
            new Car("Lada", "Niva", 2021)
    };

    @GetMapping(value = "/cars")
    public String carPage(Model model,
                          @RequestParam(value = "count", required = false) String textCount) {
        int count = 0;
        try {
            count = textCount == null ? 0 : Integer.parseInt(textCount);
        } catch (Exception e) {
            System.out.println("Готовь жеппу, хакер");
        }
        if (count > 0 && count < 5) {
            model.addAttribute("cars", Arrays.asList(Arrays.copyOfRange(carArray, 0, count)));
        } else {
            model.addAttribute("cars", Arrays.asList(carArray));
        }
        return "cars";
    }
}
