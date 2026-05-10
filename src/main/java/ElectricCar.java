/**
 * Клас, що описує електричний автомобіль (нащадок {@link Car}).
 * Особливість: витрати палива замінені на витрати електроенергії (кВт·год / 100 км).
 */
public class ElectricCar extends Car {

    /** Запас ходу на одному заряді (км) */
    private int rangeKm;

    /** Час заряджання від 0 до 100% (год) */
    private double chargeTimeHours;

    /**
     * Конструктор електромобіля.
     *
     * @param brand            марка
     * @param model            модель
     * @param year             рік випуску
     * @param price            ціна
     * @param kwhPer100km      витрати електроенергії (кВт·год/100 км) — передається як fuelConsumption
     * @param maxSpeed         максимальна швидкість
     * @param rangeKm          запас ходу (км)
     * @param chargeTimeHours  час повного заряджання (год)
     * @throws IllegalArgumentException якщо rangeKm <= 0 або chargeTimeHours <= 0
     */
    public ElectricCar(String brand, String model, int year, double price, double kwhPer100km, int maxSpeed,
        int rangeKm, double chargeTimeHours) {
        super(brand, model, year, price, kwhPer100km, maxSpeed);
        if (rangeKm <= 0)
            throw new IllegalArgumentException("Запас ходу має бути > 0: " + rangeKm);
        if (chargeTimeHours <= 0)
            throw new IllegalArgumentException("Час заряджання має бути > 0: " + chargeTimeHours);
        this.rangeKm = rangeKm;
        this.chargeTimeHours = chargeTimeHours;
    }

    /** @return запас ходу на одному заряді (км) */
    public int getRangeKm() { return rangeKm; }

    /** @return час повного заряджання (год) */
    public double getChargeTimeHours() { return chargeTimeHours; }

    /**
     * {@inheritDoc}
     *
     * @return "Електромобіль"
     */
    @Override
    public String getCarType() { return "Електромобіль"; }

    /**
     * {@inheritDoc}
     * Для електромобіля «паливо» означає кВт·год/100 км.
     */
    @Override
    public String toString() {
        return String.format(
            "[%s] %s %s (%d) | Ціна: %.0f грн | Витрати: %.1f кВт·год/100км | Макс. швидкість: %d км/год | Запас ходу: %d км | Зарядка: %.1f год",
            getCarType(), brand, model, year, price, fuelConsumption, maxSpeed, rangeKm, chargeTimeHours
        );
    }
}