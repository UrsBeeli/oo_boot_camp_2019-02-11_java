package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Node {
  private static final double UNREACHABLE = Double.POSITIVE_INFINITY;

  private Set<Node> destinations = new HashSet<>();

  public Node addPathTo(final Node destination) {
    destinations.add(destination);
    return destination;
  }

  public boolean canReach(final Node destination) {
    return hopCount(destination, new HashSet<>()) != UNREACHABLE;
  }

  public int hopCount(Node destination) {
    final double hopCount = hopCount(destination, new HashSet<>());
    if (hopCount == UNREACHABLE) {
      throw new IllegalArgumentException("Cannot reach destination");
    }
    return (int) hopCount;
  }

  private double hopCount(Node destination, Set<Node> visitedNodes) {
    if (this == destination) {
      return 0;
    }
    if (visitedNodes.contains(this)) {
      return UNREACHABLE;
    }

    return destinations.stream()
                       .mapToDouble(d -> d.hopCount(destination, copyWithThis(visitedNodes)) + 1)
                       .min()
                       .orElse(UNREACHABLE);
  }

  private Set<Node> copyWithThis(Set<Node> list) {
    Set<Node> result = new HashSet<>(list);
    result.add(this);
    return result;
  }
}
