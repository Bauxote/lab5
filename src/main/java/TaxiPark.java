import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Клас {@code TaxiPark} представляє таксопарк — колекцію автомобілів.
 *
 * <p>Підтримувані операції:
 * <ul>
 *   <li>Додавання / видалення автомобілів</li>
 *   <li>Підрахунок загальної вартості парку</li>
 *   <li>Сортування за витратами палива</li>
 *   <li>Пошук за діапазоном максимальної швидкості</li>
 * </ul>
 *
 * @author TaxiPark System
 * @version 1.0
 */
public class TaxiPark {

    /** Назва таксопарку */
    private String name;

    /** Список автомобілів у парку */
    private List<Car> cars;

    /**
     * Конструктор таксопарку.
     *
     * @param name назва таксопарку
     * @throws IllegalArgumentException якщо назва порожня
     */
    public TaxiPark(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Назва таксопарку не може бути порожньою.");
        this.name = name;
        this.cars = new ArrayList<>();
    }

    /**
     * Додає автомобіль до парку.
     *
     * @param car автомобіль для додавання
     * @throws IllegalArgumentException якщо передано null
     */
    public void addCar(Car car) {
        if (car == null)
            throw new IllegalArgumentException("Неможливо додати null-автомобіль.");
        cars.add(car);
    }

    /**
     * Видаляє автомобіль із парку за індексом.
     *
     * @param index індекс автомобіля в списку
     * @throws IndexOutOfBoundsException якщо індекс виходить за межі
     */
    public void removeCar(int index) {
        if (index < 0 || index >= cars.size())
            throw new IndexOutOfBoundsException("Невірний індекс: " + index + ". Розмір парку: " + cars.size());
        cars.remove(index);
    }

    /**
     * Повертає незмінний список автомобілів.
     *
     * @return список {@link Car}
     */
    public List<Car> getCars() {
        return List.copyOf(cars);
    }

    /**
     * Повертає назву таксопарку.
     *
     * @return рядок із назвою
     */
    public String getName() { return name; }

    /**
     * Обчислює загальну вартість усіх автомобілів у парку.
     *
     * @return сума цін усіх автомобілів у гривнях
     * @throws IllegalStateException якщо парк порожній
     */
    public double getTotalValue() {
        if (cars.isEmpty())
            throw new IllegalStateException("Таксопарк «" + name + "» порожній — вартість не може бути розрахована.");
        return cars.stream().mapToDouble(Car::getPrice).sum();
    }

    /**
     * Повертає новий список автомобілів, відсортованих за витратами палива
     * (від найменшого до найбільшого).
     *
     * @return відсортований список {@link Car}
     * @throws IllegalStateException якщо парк порожній
     */
    public List<Car> sortByFuelConsumption() {
        if (cars.isEmpty())
            throw new IllegalStateException("Таксопарк «" + name + "» порожній — сортування неможливе.");
        return cars.stream().sorted(Comparator.comparingDouble(Car::getFuelConsumption)).collect(Collectors.toList());
    }

    /**
     * Знаходить автомобілі, максимальна швидкість яких входить у заданий діапазон.
     *
     * @param minSpeed мінімальна швидкість (км/год), включно
     * @param maxSpeed максимальна швидкість (км/год), включно
     * @return список автомобілів, що відповідають критерію; може бути порожнім
     * @throws IllegalArgumentException якщо {@code minSpeed > maxSpeed} або значення від'ємні
     */
    public List<Car> findBySpeedRange(int minSpeed, int maxSpeed) {
        if (minSpeed < 0 || maxSpeed < 0)
            throw new IllegalArgumentException("Швидкість не може бути від'ємною.");
        if (minSpeed > maxSpeed)
            throw new IllegalArgumentException(
                "Мінімальна швидкість (" + minSpeed + ") не може перевищувати максимальну (" + maxSpeed + ").");
        return cars.stream().filter(c -> c.getMaxSpeed() >= minSpeed && c.getMaxSpeed() <= maxSpeed).collect(Collectors.toList());
    }

    /**
     * Повертає кількість автомобілів у парку.
     *
     * @return ціле число — розмір парку
     */
    public int size() { return cars.size(); }

    /**
     * Виводить повну інформацію про таксопарк у консоль.
     */
    public void printInfo() {
        System.out.println("═".repeat(80));
        System.out.printf("  ТАКСОПАРК: %s  (%d авт.)%n", name, cars.size());
        System.out.println("═".repeat(80));
        if (cars.isEmpty()) {
            System.out.println("  (парк порожній)");
        } else {
            for (int i = 0; i < cars.size(); i++) {
                System.out.printf("  %2d. %s%n", i + 1, cars.get(i));
            }
        }
        System.out.println("─".repeat(80));
    }
}