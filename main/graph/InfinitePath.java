package graph;

public class InfinitePath extends Path {
  InfinitePath() {
  }

  @Override
  public int hopCount() {
    return Integer.MAX_VALUE;
  }

  @Override
  public double cost() {
    return Double.POSITIVE_INFINITY;
  }
}
