package graph;

import java.util.Set;
import java.util.function.Function;

class Path {
  private final Node target;
  private final Integer cost;

  Path(Node target, Integer cost) {
    this.target = target;
    this.cost = cost;
  }

  double weight(Node destination, Set<Node> visitedNodes, Function<Path, Integer> weight) {
    return target.weight(destination, visitedNodes, weight) + weight.apply(this);
  }

  Integer hops() {
    return 1;
  }

  Integer weight() {
    return cost;
  }
}
