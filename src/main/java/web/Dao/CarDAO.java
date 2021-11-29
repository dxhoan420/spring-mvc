package web.Dao;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.Arrays;
import java.util.List;

@Component
public class CarDAO {
    private final Car[] carArray = {
            new Car("Toyota", "Celica", 2000),
            new Car("Subaru", "BRZ", 2010),
            new Car("Tesla", "model S", 2020),
            new Car("Ford", "model T", 1910),
            new Car("Lada", "Niva", 2021)
    };

    public List<Car> getCars(int count) {
        if (count > 0 && count < 5) {
            return Arrays.asList(Arrays.copyOfRange(carArray, 0, count));
        } else {
            return  Arrays.asList(carArray);
        }
    }
}
