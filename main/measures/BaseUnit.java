package measures;

public abstract class BaseUnit {
  BaseUnit() {
    this.baseUnit = this;
    this.amountInBaseUnit = 1;
    this.offset = 0;
  }

  BaseUnit(boolean supportsArithmetic) {
    this.baseUnit = this;
    this.amountInBaseUnit = 1;
    this.offset = 0;
  }

  BaseUnit(double amountInBaseUnit, BaseUnit lowerUnit) {
    this.amountInBaseUnit = amountInBaseUnit * lowerUnit.amountInBaseUnit;
    this.baseUnit = lowerUnit.baseUnit;
    this.offset = 0;
  }

  BaseUnit(double amountInBaseUnit, double offset, BaseUnit lowerUnit) {
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

  boolean isCompatible(BaseUnit other) {
    return baseUnit.equals(other.baseUnit);
  }

  public double convertedAmount(double sourceAmount, BaseUnit sourceUnit) {
    if (!isCompatible(sourceUnit)) {
      throw new IllegalArgumentException("Cannot convert between different types");
    }
    return this.convertFromBaseunit(sourceUnit.convertToBaseunit(sourceAmount));
  }
}
