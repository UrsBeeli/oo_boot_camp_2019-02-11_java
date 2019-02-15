package graph;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Link {
  private final Node target;
  private final double cost;

  Link(Node target, double cost) {
    this.target = target;
    this.cost = cost;
  }

  Stream<Path> paths(Node destination, Set<Node> visitedNodes) {
    return target.paths(destination, visitedNodes).map(path -> path.prepend(this));
  }

  static double totalPathLength(final List<Link> links) {
    return links.stream()
                .mapToDouble(link -> link.cost)
                .sum();
  }
}
