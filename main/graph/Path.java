package graph;

import java.util.Set;

class Path {
  interface WeightStrategy { double weight(double cost); }

  static final WeightStrategy LEAST_COST = cost -> cost;
  static final WeightStrategy FEWEST_HOPS = ignore -> 1;

  private final Node target;
  private final double cost;

  Path(Node target, double cost) {
    this.target = target;
    this.cost = cost;
  }

  double weight(Node destination, Set<Node> visitedNodes, WeightStrategy weightStrategy) {
    return target.weight(destination, visitedNodes, weightStrategy) + weightStrategy.weight(cost);
  }
}
