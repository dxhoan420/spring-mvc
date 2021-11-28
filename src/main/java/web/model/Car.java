package web.model;

import java.util.Objects;

public class Car {
    private String  brand;
    private String  model;
    private int     year;

    public Car(String brand, String model, int year) {
        this.brand = brand == null ? "null" : brand;
        this.model = model == null ? "null" : model;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && brand.equals(car.brand) && model.equals(car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year);
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
