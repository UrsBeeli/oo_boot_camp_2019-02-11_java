package measures;

public class Unit {
  public static final Unit TEASPOON = new Unit();
  public static final Unit TABLESPOON = new Unit(3, TEASPOON);
  public static final Unit OUNCE = new Unit(2, TABLESPOON);
  public static final Unit CUP = new Unit(8, OUNCE);
  public static final Unit PINT = new Unit(2, CUP);
  public static final Unit QUART = new Unit(2, PINT);
  public static final Unit GALLON = new Unit(4, QUART);

  public static final Unit INCH = new Unit();
  public static final Unit FOOT = new Unit(12, INCH);
  public static final Unit YARD = new Unit(3, FOOT);
  public static final Unit CHAIN = new Unit(22, YARD);
  public static final Unit FURLONG = new Unit(10, CHAIN);
  public static final Unit MILE = new Unit(8, FURLONG);

  public static final Unit SQUARE_INCH = new Unit();
  public static final Unit SQUARE_FOOT = new Unit(144, SQUARE_INCH);
  public static final Unit SQUARE_YARD = new Unit(9, SQUARE_FOOT);
  public static final Unit SQUARE_CHAIN = new Unit(484, SQUARE_YARD);
  public static final Unit SQUARE_FURLONG = new Unit(100, SQUARE_CHAIN);
  public static final Unit SQUARE_MILE = new Unit(64, SQUARE_FURLONG);

  //°F = (°C × 9/5) + 32
  //°C = (°F - 32)/1.8
  public static final Unit CELSIUS = new Unit();
  public static final Unit FAHRENHEIT = new Unit((5.0 / 9.0), 32, CELSIUS);
  public static final Unit KELVIN = new Unit(1.0, -273, CELSIUS);

  Unit() {
    this.baseUnit = this;
    this.amountInBaseUnit = 1;
    this.offset = 0;
  }

  Unit(double amountInBaseUnit, Unit lowerUnit) {
    this.amountInBaseUnit = amountInBaseUnit * lowerUnit.amountInBaseUnit;
    this.baseUnit = lowerUnit.baseUnit;
    this.offset = 0;
  }

  Unit(double amountInBaseUnit, double offset, Unit lowerUnit) {
    this.amountInBaseUnit = amountInBaseUnit * lowerUnit.amountInBaseUnit;
    this.baseUnit = lowerUnit.baseUnit;
    this.offset = offset;
  }

  private final double amountInBaseUnit;
  private final double offset;
  private final Object baseUnit;

  double convertToBaseunit(double value) {
    return (value - offset) * amountInBaseUnit;
  }

  private double convertFromBaseunit(double value) {
    return (value / amountInBaseUnit) + offset;
  }

  boolean isCompatible(Unit other) {
    return baseUnit.equals(other.baseUnit);
  }

  public double convertedAmount(double sourceAmount, Unit sourceUnit) {
    if (!isCompatible(sourceUnit)) {
      throw new IllegalArgumentException("Cannot convert between different types");
    }
    return this.convertFromBaseunit(sourceUnit.convertToBaseunit(sourceAmount));
  }
}
