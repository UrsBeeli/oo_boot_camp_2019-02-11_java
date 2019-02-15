package graph;

import java.util.ArrayList;
import java.util.List;

public class Path {

  interface WeightStrategy {
    double weight(Path path);
  }

  static final WeightStrategy LEAST_COST = path -> path.cost();
  static final WeightStrategy FEWEST_HOPS = path -> path.hops();

  private List<Link> links = new ArrayList<>();
  private final WeightStrategy weightStrategy;

  Path(WeightStrategy weightStrategy) {
    this.weightStrategy = weightStrategy;
  }

  int compareTo(Path other) {
    return Double.compare(weightStrategy.weight(this), weightStrategy.weight(other));
  }

  void prepend(final Link link) {
    links.add(0, link);
  }

  int hops() {
    return links.size();
  }

  double cost() {
    return Link.totalPathLength(links);
  }
}
