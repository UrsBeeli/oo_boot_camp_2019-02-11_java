package measures;

public abstract class BaseUnit {
  BaseUnit() {
    this.baseUnit = this;
    this.amountInBaseUnit = 1;
    this.offset = 0;
    this.supportsArithmetic = true;
  }

  BaseUnit(boolean supportsArithmetic) {
    this.baseUnit = this;
    this.amountInBaseUnit = 1;
    this.offset = 0;
    this.supportsArithmetic = supportsArithmetic;
  }

  BaseUnit(double amountInBaseUnit, BaseUnit lowerUnit) {
    this.amountInBaseUnit = amountInBaseUnit * lowerUnit.amountInBaseUnit;
    this.baseUnit = lowerUnit.baseUnit;
    this.offset = 0;
    this.supportsArithmetic = lowerUnit.supportsArithmetic;
  }

  BaseUnit(double amountInBaseUnit, double offset, BaseUnit lowerUnit) {
    this.amountInBaseUnit = amountInBaseUnit * lowerUnit.amountInBaseUnit;
    this.baseUnit = lowerUnit.baseUnit;
    this.offset = offset;
    this.supportsArithmetic = lowerUnit.supportsArithmetic;
  }

  private final double amountInBaseUnit;
  private final double offset;
  private final Object baseUnit;
  private final boolean supportsArithmetic;

  double convertToBaseunit(double value) {
    return (value - offset) * amountInBaseUnit;
  }

  private double convertFromBaseunit(double value) {
    return (value / amountInBaseUnit) + offset;
  }

  boolean isCompatible(BaseUnit other) {
    return baseUnit.equals(other.baseUnit);
  }

  boolean supportsArithmatic() {
    return supportsArithmetic;
  }

  public double convertedAmount(double sourceAmount, BaseUnit sourceUnit) {
    if (!isCompatible(sourceUnit)) {
      throw new IllegalArgumentException("Cannot convert between different types");
    }
    return this.convertFromBaseunit(sourceUnit.convertToBaseunit(sourceAmount));
  }
}
