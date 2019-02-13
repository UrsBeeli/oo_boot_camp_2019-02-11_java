package measures;

public class IntervalQuantity<T extends BaseUnit> extends Quantity<T> {

  IntervalQuantity(final double amount, final T unit) {
    super(amount, unit);
  }

  public Quantity<T> add(final Quantity<T> other) {
    compatibilityCheck(other);
    return new Quantity<>(this.amount + this.unit.convertedAmount(other.amount, other.unit), this.unit);
  }

  public Quantity<T> subtract(final Quantity<T> other) {
    compatibilityCheck(other);
    return new Quantity<>(this.amount - this.unit.convertedAmount(other.amount, other.unit), this.unit);
  }

  private void compatibilityCheck(final Quantity<T> other) {
    if (!unit.isCompatible(other.unit)) {
      throw new IllegalArgumentException("Arithmetic operations not allowed on different unit types");
    }
  }

}
