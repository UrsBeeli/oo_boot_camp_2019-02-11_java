package measures;

public enum Unit {
  TEASPOON(0, null),
  TABLESPOON(3, TEASPOON),
  OUNCE(2, TABLESPOON),
  CUP(8, OUNCE),
  PINT(2, CUP),
  QUART(2, PINT),
  GALLON(4, QUART);

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
