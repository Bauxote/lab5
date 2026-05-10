/**
 * Узагальнений клас, що описує легковий автомобіль.
 * Є базовим класом для ієрархії автомобілів таксопарку.
 *
 */
public abstract class Car {

    /** Марка автомобіля */
    protected String brand;

    /** Модель автомобіля */
    protected String model;

    /** Рік випуску */
    protected int year;

    /** Ціна автомобіля (в грн) */
    protected double price;

    /** Витрати палива (л/100 км) */
    protected double fuelConsumption;

    /** Максимальна швидкість (км/год) */
    protected int maxSpeed;

    /**
     * Конструктор з параметрами для ініціалізації основних характеристик автомобіля.
     *
     * @param brand          марка автомобіля
     * @param model          модель автомобіля
     * @param year           рік випуску
     * @param price          ціна автомобіля
     * @param fuelConsumption витрати палива (л/100 км)
     * @param maxSpeed       максимальна швидкість (км/год)
     * @throws IllegalArgumentException якщо передані некоректні дані
     */
    public Car(String brand, String model, int year, double price, double fuelConsumption, int maxSpeed) {
        if (brand == null || brand.isBlank())
            throw new IllegalArgumentException("Марка автомобіля не може бути порожньою.");
        if (model == null || model.isBlank())
            throw new IllegalArgumentException("Модель автомобіля не може бути порожньою.");
        if (year < 1886 || year > 2100)
            throw new IllegalArgumentException("Некоректний рік випуску: " + year);
        if (price < 0)
            throw new IllegalArgumentException("Ціна не може бути від'ємною: " + price);
        if (fuelConsumption <= 0)
            throw new IllegalArgumentException("Витрати палива мають бути > 0: " + fuelConsumption);
        if (maxSpeed <= 0)
            throw new IllegalArgumentException("Максимальна швидкість має бути > 0: " + maxSpeed);

        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.fuelConsumption = fuelConsumption;
        this.maxSpeed = maxSpeed;
    }

    /** @return марка автомобіля */
    public String getBrand() { return brand; }

    /** @return модель автомобіля */
    public String getModel() { return model; }

    /** @return рік випуску */
    public int getYear() { return year; }

    /** @return ціна автомобіля */
    public double getPrice() { return price; }

    /** @return витрати палива (л/100 км) */
    public double getFuelConsumption() { return fuelConsumption; }

    /** @return максимальна швидкість (км/год) */
    public int getMaxSpeed() { return maxSpeed; }

    /**
     * Метод, що повертає тип автомобіля.
     *
     * @return рядок із назвою типу автомобіля
     */
    public abstract String getCarType();

    /**
     * Повертає рядкове представлення автомобіля.
     *
     * @return форматований рядок з характеристиками
     */
    @Override
    public String toString() {
        return String.format(
            "[%s] %s %s (%d) | Ціна: %.0f грн | Паливо: %.1f л/100км | Макс. швидкість: %d км/год",
            getCarType(), brand, model, year, price, fuelConsumption, maxSpeed
        );
    }
}