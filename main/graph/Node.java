package graph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {
  private Set<Node> destinations = new HashSet<>();

  public Node addPathTo(final Node destination) {
    destinations.add(destination);
    return destination;
  }

  public boolean canReach(final Node destination) {
    return canReach(destination, new HashSet<>());
  }

  private boolean canReach(final Node destination, final Set<Node> alreadyVisited) {
    if (destination == this) {
      return true;
    }
    if (alreadyVisited.contains(this)) {
      return false;
    }

    alreadyVisited.add(this);
    return destinations.stream().anyMatch(d -> d.canReach(destination, alreadyVisited));
  }

  public Integer hopCount(Node destination) {
    return hopCount(destination, new HashSet<>());
  }

  private Integer hopCount(Node destination, Set<Node> visitedNodes) {
    if (this == destination) {
      return 0;
    }
    if (visitedNodes.contains(this)) {
      return null;
    }
    visitedNodes.add(this);

    return destinations.stream()
                       .map(d -> d.hopCount(destination, visitedNodes))
                       .filter(Objects::nonNull)
                       .map(hop -> hop + 1)
                       .sorted()
                       .findFirst()
                       .orElse(null);
  }
}
