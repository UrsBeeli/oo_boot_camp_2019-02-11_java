package measures;


import java.util.Objects;

public class Quantity<T extends BaseUnit> {
  final double amount;
  final T unit;

  protected Quantity(final double amount, final T unit) {
    Objects.requireNonNull(unit, "Unit must not be null");

    this.amount = amount;
    this.unit = unit;
  }

  public Quantity<T> convertTo(T targetUnit) {
    if (!unit.isCompatible(targetUnit)) {
      throw new IllegalArgumentException("Cannot convert between different unit types");
    }
    return new Quantity<>(targetUnit.convertedAmount(this.amount, this.unit), targetUnit);
  }


  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Quantity)) {
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
