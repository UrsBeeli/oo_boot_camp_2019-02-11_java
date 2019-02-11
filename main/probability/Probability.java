package probability;

import java.util.Objects;

public class Probability {
  private final double probability;

  public Probability(final double probability) {
    if (probability < 0.0 || probability > 1.0) {
      throw new IllegalArgumentException(String.format("Probability %f not in range [0.0 .. 1.0]", probability));
    }
    this.probability = probability;
  }

  public boolean isMoreLikely(final Probability other) {
    return probability > other.probability;
  }

  public boolean isLessLikely(final Probability other) {
    return probability < other.probability;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || this.getClass() != other.getClass()) {
      return false;
    }
    final Probability that = (Probability) other;
    return Double.compare(that.probability, this.probability) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(probability);
  }


  public Probability and(final Probability probability) {
    return null;
  }

  public Probability or(final Probability other) {
    return null;
  }

  public Probability not() {
    return null;
  }

}
