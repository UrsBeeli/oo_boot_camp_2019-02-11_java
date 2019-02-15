package measures;

public class Temperature extends BaseUnit {

  public static final Temperature BASE_TEMPERATURE = new Temperature();
  //°F = (°C × 9/5) + 32
  //°C = (°F - 32)/1.8
  public static final Temperature CELSIUS = new Temperature(1, 0, BASE_TEMPERATURE);
  public static final Temperature FAHRENHEIT = new Temperature((5.0 / 9.0), 32, CELSIUS);
  public static final Temperature KELVIN = new Temperature(1.0, 273, CELSIUS);

  private Temperature() {
  }

  private Temperature(double amountInBaseUnit, double offset, BaseUnit lowerUnit) {
    super(amountInBaseUnit, offset, lowerUnit);
  }

  public Quantity<Temperature> s(double amount) {
    return new Quantity<>(amount, this);
  }
}
