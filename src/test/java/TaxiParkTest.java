import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/**
 * JUnit tests for the TaxiPark system.
 */
public class TaxiParkTest {

    private TaxiPark park;
    private EconomyCar economy;
    private BusinessCar business;
    private PremiumCar premium;
    private ElectricCar electric;

    @Before
    public void setUp() {
        park    = new TaxiPark("Test Fleet");
        economy = new EconomyCar("Toyota",   "Yaris",    2020, 100_000, 5.0, 160, 4);
        business= new BusinessCar("BMW",     "5 Series", 2021, 300_000, 9.0, 220, true, true);
        premium = new PremiumCar("Mercedes", "S-Class",  2022, 500_000, 11.0, 250, 400, "Автомат");
        electric= new ElectricCar("Tesla",   "Model 3",  2022, 400_000, 15.0, 225, 580, 0.5);
    }

    // EconomyCar

    @Test
    public void economyCar_fieldsStoredCorrectly() {
        assertEquals("Toyota", economy.getBrand());
        assertEquals("Yaris",  economy.getModel());
        assertEquals(2020,     economy.getYear());
        assertEquals(100_000,  economy.getPrice(), 0);
        assertEquals(5.0,      economy.getFuelConsumption(), 0);
        assertEquals(160,      economy.getMaxSpeed());
        assertEquals(4,        economy.getPassengerSeats());
    }

    @Test
    public void economyCar_getCarType() {
        assertEquals("Економ-клас", economy.getCarType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void economyCar_zeroSeats_throws() {
        new EconomyCar("A", "B", 2020, 100, 5.0, 120, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void economyCar_tooManySeats_throws() {
        new EconomyCar("A", "B", 2020, 100, 5.0, 120, 6);
    }

    // BusinessCar

    @Test
    public void businessCar_getCarType() {
        assertEquals("Бізнес-клас", business.getCarType());
    }

    @Test
    public void businessCar_optionsStored() {
        assertTrue(business.isHasAirConditioning());
        assertTrue(business.isHasLeatherInterior());

        BusinessCar plain = new BusinessCar("Kia", "K5", 2022, 200_000, 7.5, 200, false, false);
        assertFalse(plain.isHasAirConditioning());
        assertFalse(plain.isHasLeatherInterior());
    }

    // PremiumCar

    @Test
    public void premiumCar_getCarType() {
        assertEquals("Преміум-клас", premium.getCarType());
    }

    @Test
    public void premiumCar_fieldsStored() {
        assertEquals(400,        premium.getHorsePower());
        assertEquals("Автомат",  premium.getTransmissionType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void premiumCar_zeroHorsePower_throws() {
        new PremiumCar("A", "B", 2020, 100, 9.0, 200, 0, "Авт");
    }

    @Test(expected = IllegalArgumentException.class)
    public void premiumCar_blankTransmission_throws() {
        new PremiumCar("A", "B", 2020, 100, 9.0, 200, 300, "");
    }

    // ElectricCar

    @Test
    public void electricCar_getCarType() {
        assertEquals("Електромобіль", electric.getCarType());
    }

    @Test
    public void electricCar_fieldsStored() {
        assertEquals(580, electric.getRangeKm());
        assertEquals(0.5, electric.getChargeTimeHours(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void electricCar_zeroRange_throws() {
        new ElectricCar("A", "B", 2020, 100, 14.0, 200, 0, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void electricCar_zeroChargeTime_throws() {
        new ElectricCar("A", "B", 2020, 100, 14.0, 200, 400, 0);
    }

    // Car base validation

    @Test(expected = IllegalArgumentException.class)
    public void car_blankBrand_throws() {
        new EconomyCar("", "Yaris", 2020, 100, 5.0, 150, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void car_nullModel_throws() {
        new EconomyCar("Toyota", null, 2020, 100, 5.0, 150, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void car_invalidYear_throws() {
        new EconomyCar("Toyota", "Yaris", 1800, 100, 5.0, 150, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void car_negativePrice_throws() {
        new EconomyCar("Toyota", "Yaris", 2020, -1, 5.0, 150, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void car_zeroFuelConsumption_throws() {
        new EconomyCar("Toyota", "Yaris", 2020, 100, 0, 150, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void car_zeroMaxSpeed_throws() {
        new EconomyCar("Toyota", "Yaris", 2020, 100, 5.0, 0, 4);
    }

    // TaxiPark basics

    @Test
    public void taxiPark_getName() {
        assertEquals("Test Fleet", park.getName());
    }

    @Test
    public void taxiPark_initiallyEmpty() {
        assertEquals(0, park.size());
    }

    @Test
    public void taxiPark_addAndRemove() {
        park.addCar(economy);
        assertEquals(1, park.size());
        park.removeCar(0);
        assertEquals(0, park.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void taxiPark_blankName_throws() {
        new TaxiPark("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void taxiPark_addNull_throws() {
        park.addCar(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void taxiPark_removeInvalidIndex_throws() {
        park.removeCar(99);
    }

    // getTotalValue
    @Test
    public void getTotalValue_sumIsCorrect() {
        park.addCar(economy);   // 100_000
        park.addCar(business);  // 300_000
        park.addCar(premium);   // 500_000
        assertEquals(900_000, park.getTotalValue(), 0);
    }

    @Test(expected = IllegalStateException.class)
    public void getTotalValue_emptyPark_throws() {
        park.getTotalValue();
    }

    // sortByFuelConsumption

    @Test
    public void sortByFuelConsumption_correctOrder() {
        park.addCar(new EconomyCar("A", "X", 2020, 100, 9.0, 150, 4));
        park.addCar(new EconomyCar("B", "Y", 2020, 100, 5.0, 150, 4));
        park.addCar(new EconomyCar("C", "Z", 2020, 100, 7.0, 150, 4));

        List<Car> sorted = park.sortByFuelConsumption();
        assertEquals(5.0, sorted.get(0).getFuelConsumption(), 0);
        assertEquals(7.0, sorted.get(1).getFuelConsumption(), 0);
        assertEquals(9.0, sorted.get(2).getFuelConsumption(), 0);
    }

    @Test
    public void sortByFuelConsumption_doesNotMutateOriginal() {
        park.addCar(new EconomyCar("A", "X", 2020, 100, 9.0, 150, 4));
        park.addCar(new EconomyCar("B", "Y", 2020, 100, 5.0, 150, 4));

        park.sortByFuelConsumption();

        assertEquals(9.0, park.getCars().get(0).getFuelConsumption(), 0);
    }

    @Test(expected = IllegalStateException.class)
    public void sortByFuelConsumption_emptyPark_throws() {
        park.sortByFuelConsumption();
    }

    // findBySpeedRange
    @Test
    public void findBySpeedRange_findsCorrectCar() {
        park.addCar(economy);   // 160
        park.addCar(business);  // 220
        park.addCar(premium);   // 250

        List<Car> found = park.findBySpeedRange(200, 230);
        assertEquals(1, found.size());
        assertEquals(220, found.get(0).getMaxSpeed());
    }

    @Test
    public void findBySpeedRange_boundariesAreInclusive() {
        park.addCar(economy);   // 160
        park.addCar(business);  // 220

        List<Car> found = park.findBySpeedRange(160, 220);
        assertEquals(2, found.size());
    }

    @Test
    public void findBySpeedRange_noMatch_returnsEmptyList() {
        park.addCar(economy);
        assertTrue(park.findBySpeedRange(300, 400).isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findBySpeedRange_minGreaterThanMax_throws() {
        park.findBySpeedRange(300, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findBySpeedRange_negativeMin_throws() {
        park.findBySpeedRange(-1, 200);
    }
}