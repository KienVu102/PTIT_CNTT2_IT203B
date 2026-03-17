package Ex_2;

public class ThermometerAdapter implements TemperatureSensor{

    private OldThermometer oldThermometer;

    public ThermometerAdapter(OldThermometer oldThermometer) {
        this.oldThermometer = oldThermometer;
    }

    @Override
    public void setTemperature(double inTemperature) {
        // Since this is an adapter for an old thermometer that only reads temperature,
        // we don't implement setting temperature as it doesn't make sense for this device
        System.out.println("Old thermometer cannot set temperature - read only device");
    }

    @Override
    public double getTemperatureCelsius() {
        return (oldThermometer.getTemperatureFahrenheit() - 32) * 5/9 ;
    }
}
