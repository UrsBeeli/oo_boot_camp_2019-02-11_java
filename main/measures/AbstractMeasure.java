package measures;


import java.util.Objects;

public abstract class AbstractMeasure<T extends AbstractUnit> {
  final double amount;
  final T unit;

  protected abstract AbstractMeasure buildMeasure(final double amount, final T unit);

  protected abstract T getBaseUnit();

  protected AbstractMeasure(final double amount, final T unit) {
    Objects.requireNonNull(unit, "Unit must not be null");

    this.amount = amount;
    this.unit = unit;
  }

  public AbstractMeasure convertTo(T targetUnit) {
    return buildMeasure(targetUnit.convertedAmount(this.amount, this.unit), targetUnit);
  }

  public AbstractMeasure add(final AbstractMeasure other) {
    return buildMeasure(this.amount + this.unit.convertedAmount(other.amount, other.unit), this.unit);
  }

  public AbstractMeasure subtract(final AbstractMeasure other) {
    return buildMeasure(this.amount - this.unit.convertedAmount(other.amount, other.unit), this.unit);
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    final AbstractMeasure that = (AbstractMeasure) other;
    return this.amount == this.unit.convertedAmount(that.amount, that.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getBaseUnit().convertedAmount(this.amount, this.unit));
  }

  public String toString() {
    return String.format("%f %s", amount, unit);
  }
}
