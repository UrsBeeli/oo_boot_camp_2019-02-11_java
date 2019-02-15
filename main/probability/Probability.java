package probability;

import java.util.Objects;

import finder.Comparable;

public class Probability implements Comparable<Probability> {
  private static final double CERTAINTY = 1.0;
  private static final double MAX_EPSILON = 0.0000000001;

  private final double likelyhoodFraction;

  public Probability(final double likelyhoodFraction) {
    if (likelyhoodFraction < 0.0 || likelyhoodFraction > 1.0) {
      throw new IllegalArgumentException(String.format("Probability %f not in range [0.0 .. 1.0]", likelyhoodFraction));
    }
    this.likelyhoodFraction = likelyhoodFraction;
  }

  public boolean isMoreLikely(final Probability other) {
    return likelyhoodFraction > other.likelyhoodFraction;
  }

  public boolean isLessLikely(final Probability other) {
    return likelyhoodFraction < other.likelyhoodFraction;
  }

  public Probability and(final Probability other) {
    return new Probability(likelyhoodFraction * other.likelyhoodFraction);
  }

  public Probability or(final Probability other) {
    //return new Probability(likelyhoodFraction + other.likelyhoodFraction - and(other).likelyhoodFraction);
    return this.not().and(other.not()).not();
  }

  public Probability not() {
    return new Probability(CERTAINTY - likelyhoodFraction);
  }

  public Probability times(final int count) {
    return new Probability(Math.pow(likelyhoodFraction, count));
  }

  @Override
  public String toString() {
    return String.format("%f", likelyhoodFraction);
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
    //return Double.compare(that.likelyhoodFraction, this.likelyhoodFraction) == 0; // this breaks on new Probability(0.3).not().not();
    return Math.abs(that.likelyhoodFraction - this.likelyhoodFraction) < MAX_EPSILON;
  }

  @Override
  public int hashCode() {
    return Objects.hash(likelyhoodFraction);
  }

  @Override
  public boolean isBetterThan(final Probability other) {
    return likelyhoodFraction > other.likelyhoodFraction;
  }
}
