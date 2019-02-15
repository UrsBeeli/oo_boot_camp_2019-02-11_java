package graph;

public abstract class Path {
  interface WeightStrategy {
    int compareTo(final Path p1, final Path p2);
  }

  static final WeightStrategy LEAST_COST = (p1, p2) -> Double.compare(p1.cost(), p2.cost());
  static final WeightStrategy FEWEST_HOPS = (p1, p2) -> Integer.compare(p1.hops(), p2.hops());

  Path prepend(final Link link) {
    return this;
  }

  abstract int hops();

  abstract double cost();
}
