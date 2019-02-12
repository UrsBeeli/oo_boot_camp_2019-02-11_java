package measures;


import java.util.Objects;

public class Quantity {
  final double amount;
  final Unit unit;

  public Quantity(final double amount, final Unit unit) {
    Objects.requireNonNull(unit, "Unit must not be null");

    this.amount = amount;
    this.unit = unit;
  }

  public Quantity convertTo(Unit targetUnit) {
    if (!unit.isCompatible(targetUnit)) {
      throw new IllegalArgumentException("Cannot convert between different unit types");
    }
    return new Quantity(targetUnit.convertedAmount(this.amount, this.unit), targetUnit);
  }

  public Quantity add(final Quantity other) {
    if (!unit.isCompatible(other.unit)) {
      throw new IllegalArgumentException("Cannot add different unit types");
    }
    return new Quantity(this.amount + this.unit.convertedAmount(other.amount, other.unit), this.unit);
  }

  public Quantity subtract(final Quantity other) {
    if (!unit.isCompatible(other.unit)) {
      throw new IllegalArgumentException("Cannot subtract different unit types");
    }
    return new Quantity(this.amount - this.unit.convertedAmount(other.amount, other.unit), this.unit);
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    final Quantity that = (Quantity) other;
    return this.unit.isCompatible(that.unit) && this.amount == this.unit.convertedAmount(that.amount, that.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(unit.convertToBaseunit(this.amount));
  }

  public String toString() {
    return String.format("%f %s", amount, unit);
  }
}
