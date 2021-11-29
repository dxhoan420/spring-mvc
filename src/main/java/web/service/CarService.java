package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.Dao.CarDAO;
import web.model.Car;
import java.util.List;

@Component
public class CarService {
    private final CarDAO carDAO;

    @Autowired
    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public List<Car> getCars(int count) {
        return carDAO.getCars(count);
    }
}
