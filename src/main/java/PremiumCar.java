/**
 * Клас, що описує преміум-клас автомобіля (нащадок {@link Car}).
 */
public class PremiumCar extends Car {

    /** Потужність двигуна (к.с.) */
    private int horsePower;

    /** Тип трансмісії */
    private String transmissionType;

    /**
     * Конструктор преміум-класу.
     *
     * @param brand            марка
     * @param model            модель
     * @param year             рік випуску
     * @param price            ціна
     * @param fuelConsumption  витрати палива
     * @param maxSpeed         максимальна швидкість
     * @param horsePower       потужність двигуна (к.с.)
     * @param transmissionType тип трансмісії
     * @throws IllegalArgumentException якщо потужність <= 0 або тип трансмісії порожній
     */
    public PremiumCar(String brand, String model, int year, double price, double fuelConsumption, int maxSpeed, 
        int horsePower, String transmissionType) {
        super(brand, model, year, price, fuelConsumption, maxSpeed);
        if (horsePower <= 0)
            throw new IllegalArgumentException("Потужність має бути > 0: " + horsePower);
        if (transmissionType == null || transmissionType.isBlank())
            throw new IllegalArgumentException("Тип трансмісії не може бути порожнім.");
        this.horsePower = horsePower;
        this.transmissionType = transmissionType;
    }

    /** @return потужність двигуна (к.с.) */
    public int getHorsePower() { return horsePower; }

    /** @return тип трансмісії */
    public String getTransmissionType() { return transmissionType; }

    /**
     * {@inheritDoc}
     *
     * @return "Преміум-клас"
     */
    @Override
    public String getCarType() { return "Преміум-клас"; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()
            + String.format(" | Двигун: %d к.с. | Трансмісія: %s",
                horsePower, transmissionType);
    }
}