package measures;


import java.util.Objects;

public class Measure {
  private final double amount;
  private final Unit unit;

  public Measure(final double amount, final Unit unit) {
    Objects.requireNonNull(unit, "Unit must not be null");

    this.amount = amount;
    this.unit = unit;
  }

  public Measure convertTo(Unit targetUnit) {
    return new Measure(targetUnit.convertedAmount(this.amount, this.unit), targetUnit);
  }

  public Measure add(final Measure other) {
    return new Measure(this.amount + this.unit.convertedAmount(other.amount, other.unit), this.unit);
  }

  public Measure subtract(final Measure other) {
    return new Measure(this.amount - this.unit.convertedAmount(other.amount, other.unit), this.unit);
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    final Measure that = (Measure) other;
    return this.amount == this.unit.convertedAmount(that.amount, that.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Unit.TEASPOON.convertedAmount(this.amount, this.unit));
  }

  public String toString() {
    return String.format("%f %s", amount, unit);
  }

}
