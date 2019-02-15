package graph;

public class UnreachablePath extends Path{
public UnreachablePath() {
    super(LEAST_COST);
  }

  @Override
  void prepend(final Link link) {
  }

  @Override
  int hops() {
    return Integer.MAX_VALUE;
  }

  @Override
  double cost() {
    return Double.POSITIVE_INFINITY;
  }
}
