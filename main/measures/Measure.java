package measures;


import java.util.Objects;

public class Measure {
  private final double value;
  private final Unit unit;

  public Measure(final double value, final Unit unit) {
    Objects.requireNonNull(unit, "Unit must not be null");

    this.value = value;
    this.unit = unit;
  }

  public Measure convertTo(Unit targetUnit) {
    return new Measure(unit.convert(this.value, targetUnit), targetUnit);
  }

  public Measure add(final Measure other, final Unit targetUnit) {
    double teaSpoons = this.unit.convert(this.value, Unit.TEASPOON) + other.unit.convert(other.value, Unit.TEASPOON);
    return new Measure(Unit.TEASPOON.convert(teaSpoons, targetUnit), targetUnit);
  }

  public Measure subtract(final Measure other, final Unit targetUnit) {
    double teaSpoons = this.unit.convert(this.value, Unit.TEASPOON) - other.unit.convert(other.value, Unit.TEASPOON);
    return new Measure(Unit.TEASPOON.convert(teaSpoons, targetUnit), targetUnit);
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
    return this.unit.convert(this.value, Unit.TEASPOON) == that.unit.convert(that.value, Unit.TEASPOON);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.unit.convert(this.value, Unit.TEASPOON));
  }

  public String toString() {
    return String.format("%f %s", value, unit);
  }

}
