package graph;

import java.util.List;
import java.util.Set;

class Link {
  private final Node target;
  private final double cost;

  Link(Node target, double cost) {
    this.target = target;
    this.cost = cost;
  }

  Path path(Node destination, Set<Node> visitedNodes, WeightStrategy weightStrategy) {
    return target.path(destination, visitedNodes, weightStrategy).prepend(this);
  }

  static double totalPathLength(final List<Link> links) {
    return links.stream()
                .mapToDouble(link -> link.cost)
                .sum();
  }
}
