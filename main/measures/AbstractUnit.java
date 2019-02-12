package measures;

public abstract class AbstractUnit<T extends AbstractUnit> {
  AbstractUnit(int amountInLowerUnit, AbstractUnit lowerUnit) {
    this.amountInLowerUnit = amountInLowerUnit;
    this.lowerUnit = lowerUnit;
  }

  private final int amountInLowerUnit;
  private final AbstractUnit lowerUnit;

  double convertToBaseunit(double value) {
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

  public double convertedAmount(double sourceAmount, T sourceUnit) {
    return this.convertFromBaseunit(sourceUnit.convertToBaseunit(sourceAmount));
  }

  protected abstract T getBaseUnit();
}
