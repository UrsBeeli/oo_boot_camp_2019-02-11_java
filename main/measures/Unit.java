package measures;

public final class Unit {
  public static final Unit TEASPOON = new Unit(0, null);
  public static final Unit TABLESPOON = new Unit(3, TEASPOON);
  public static final Unit OUNCE = new Unit(2, TABLESPOON);
  public static final Unit CUP = new Unit(8, OUNCE);
  public static final Unit PINT = new Unit(2, CUP);
  public static final Unit QUART = new Unit(2, PINT);
  public static final Unit GALLON = new Unit(4, QUART);

  private Unit(int amountInLowerUnit, Unit lowerUnit) {
    this.amountInLowerUnit = amountInLowerUnit;
    this.lowerUnit = lowerUnit;
  }

  private final int amountInLowerUnit;
  private final Unit lowerUnit;

  private double convertToTeaSpoons(double value) {
    if (lowerUnit == null) {
      return value;
    } else {
      return lowerUnit.convertToTeaSpoons(value * amountInLowerUnit);
    }
  }

  private double convertFromTeaSpoons(double value, Unit targetUnit) {
    if (targetUnit.lowerUnit == null) {
      return value;
    } else {
      return targetUnit.convertFromTeaSpoons(value / targetUnit.amountInLowerUnit, targetUnit.lowerUnit);
    }
  }

  public double convert(double value, Unit targetUnit) {
    return convertFromTeaSpoons(convertToTeaSpoons(value), targetUnit);
  }
}
