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

  private double convertToBaseunit(double value) {
    if (lowerUnit == null) {
      return value;
    }
    return lowerUnit.convertToBaseunit(value * amountInLowerUnit);
  }

  private double convertFromBaseunit(double value) {
    if (lowerUnit == null) {
      return value;
    }
    return lowerUnit.convertFromBaseunit(value / amountInLowerUnit);
  }

  public double convertedAmount(double sourceAmount, Unit sourceUnit) {
    return this.convertFromBaseunit(sourceUnit.convertToBaseunit(sourceAmount));
  }
}
