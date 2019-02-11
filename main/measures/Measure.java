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
    return new Measure(Unit.convert(this.value, this.unit, targetUnit), targetUnit);
  }

  public Measure add(final Measure other, final Unit targetUnit) {
    double teaSpoons = Unit.convert(this.value, this.unit, Unit.TEA_SPOON) +
        Unit.convert(other.value, other.unit, Unit.TEA_SPOON);

    return new Measure(Unit.convert(teaSpoons, Unit.TEA_SPOON, targetUnit), targetUnit);
  }

  public Measure subtract(final Measure other, final Unit targetUnit) {
    double teaSpoons = Unit.convert(this.value, this.unit, Unit.TEA_SPOON) -
        Unit.convert(other.value, other.unit, Unit.TEA_SPOON);

    return new Measure(Unit.convert(teaSpoons, Unit.TEA_SPOON, targetUnit), targetUnit);
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
    return Unit.convert(this.value, this.unit, Unit.TEA_SPOON) ==
        Unit.convert(that.value, that.unit, Unit.TEA_SPOON);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Unit.convert(this.value, this.unit, Unit.TEA_SPOON));
  }

  public String toString() {
    return String.format("%f %s", value, unit);
  }

}
