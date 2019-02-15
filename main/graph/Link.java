package graph;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

class Link {
  private final Node target;
  private final double cost;

  Link(Node target, double cost) {
    this.target = target;
    this.cost = cost;
  }

  Path path(Node destination, Set<Node> visitedNodes, Comparator<Path> weightStrategy) {
    return target.path(destination, visitedNodes, weightStrategy).prepend(this);
  }

  List<Path> paths(Node destination, Set<Node> visitedNodes) {
    return target.paths(destination, visitedNodes).stream()
      .map(path -> path.prepend(this))
        .collect(toList());
  }

  static double totalPathLength(final List<Link> links) {
    return links.stream()
                .mapToDouble(link -> link.cost)
                .sum();
  }
}
