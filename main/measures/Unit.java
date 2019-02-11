package measures;

public enum Unit {
  TEA_SPOON(1),
  TABLE_SPOON(Constants.TEA_SPOONS_PER_TABLE_SPOON),
  OUNCE(Constants.TEA_SPOONS_PER_OUNCE),
  CUP(Constants.TEA_SPOONS_PER_CUP),
  PINT(Constants.TEA_SPOONS_PER_PINT),
  QUART(Constants.TEA_SPOONS_PER_QUART),
  GALLON(Constants.TEA_SPOONS_PER_GALLON);

  Unit(int amountOfTeaspoons) {
    this.amountOfTeaspoons = amountOfTeaspoons;
  }

  private int amountOfTeaspoons;

  private static double convertToTeaSpoons(double value, Unit sourceUnit) {
    return value * sourceUnit.amountOfTeaspoons;
  }

  private static double convertFromTeaSpoons(double value, Unit targetUnit) {
    return value / targetUnit.amountOfTeaspoons;
  }

  public static double convert(double value, Unit sourceUnit, Unit targetUnit) {
    return convertFromTeaSpoons(convertToTeaSpoons(value, sourceUnit), targetUnit);
  }

  private class Constants {
    static final int TEA_SPOONS_PER_TABLE_SPOON = 3;
    static final int TEA_SPOONS_PER_OUNCE = 2 * TEA_SPOONS_PER_TABLE_SPOON;
    static final int TEA_SPOONS_PER_CUP = 8 * TEA_SPOONS_PER_OUNCE;
    static final int TEA_SPOONS_PER_PINT = 2 * TEA_SPOONS_PER_CUP;
    static final int TEA_SPOONS_PER_QUART = 2 * TEA_SPOONS_PER_PINT;
    static final int TEA_SPOONS_PER_GALLON = 4 * TEA_SPOONS_PER_QUART;
  }
}
