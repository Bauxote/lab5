/**
 * Клас, що описує бізнес-клас автомобіля (нащадок {@link Car})
 * 
 */
public class BusinessCar extends Car {

    /** Наявність кондиціонера */
    private boolean hasAirConditioning;

    /** Наявність шкіряного салону */
    private boolean hasLeatherInterior;

    /**
     * Конструктор бізнес-класу.
     *
     * @param brand               марка
     * @param model               модель
     * @param year                рік випуску
     * @param price               ціна
     * @param fuelConsumption     витрати палива
     * @param maxSpeed            максимальна швидкість
     * @param hasAirConditioning  наявність кондиціонера
     * @param hasLeatherInterior  наявність шкіряного салону
     */
    public BusinessCar(String brand, String model, int year, double price, double fuelConsumption, int maxSpeed,
        boolean hasAirConditioning, boolean hasLeatherInterior) {
        super(brand, model, year, price, fuelConsumption, maxSpeed);
        this.hasAirConditioning = hasAirConditioning;
        this.hasLeatherInterior = hasLeatherInterior;
    }

    /** @return чи є кондиціонер */
    public boolean isHasAirConditioning() { return hasAirConditioning; }

    /** @return чи є шкіряний салон */
    public boolean isHasLeatherInterior() { return hasLeatherInterior; }

    /**
     * {@inheritDoc}
     *
     * @return "Бізнес-клас"
     */
    @Override
    public String getCarType() { return "Бізнес-клас"; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()
            + String.format(" | Кондиціонер: %s | Шкіра: %s",
                hasAirConditioning ? "Так" : "Ні",
                hasLeatherInterior ? "Так" : "Ні");
    }
}