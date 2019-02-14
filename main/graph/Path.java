package graph;

import java.util.Set;

class Path {
  interface WeightStrategy { double weight(double childValue, double cost, double gradient); }

  static final WeightStrategy LEAST_COST = (childValue, cost, ignoreGradient) -> childValue + cost;
  static final WeightStrategy FEWEST_HOPS = (childValue, ignoreCost, ignoreGradient) -> childValue + 1;
  static final WeightStrategy HIGHEST_GRADIENT_ON_PATH_WITH_LOWEST_GRADIENT = (childValue, ignoreCost, gradient) -> Math.max(childValue, gradient);

  private final Node target;
  private final double cost;
  private final double gradient;

  Path(Node target, double cost, double gradient) {
    this.target = target;
    this.cost = cost;
    this.gradient = gradient;
  }

  double weight(Node destination, Set<Node> visitedNodes, WeightStrategy weightStrategy) {
    return weightStrategy.weight(target.weight(destination, visitedNodes, weightStrategy), cost, gradient);
  }
}
