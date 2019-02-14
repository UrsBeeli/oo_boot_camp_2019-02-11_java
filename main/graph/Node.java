package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Node {
  private static final double UNREACHABLE = Double.POSITIVE_INFINITY;

  private Map<Node, Integer> destinations = new HashMap<>();

  public void addPathTo(final Node destination, int cost) {
    destinations.put(destination, cost);
  }

  public boolean canReach(final Node destination) {
    return hopCount(destination, new HashSet<>()) != UNREACHABLE;
  }

  public int hopCount(Node destination) {
    return valueOrThrowIfUnreachable(hopCount(destination, new HashSet<>()));
  }

  public int cost(final Node destination) {
    return valueOrThrowIfUnreachable(cost(destination, new HashSet<>()));
  }

  private double hopCount(Node destination, Set<Node> visitedNodes) {
    if (this == destination) {
      return 0;
    }
    if (visitedNodes.contains(this)) {
      return UNREACHABLE;
    }

    return destinations.keySet().stream()
                       .mapToDouble(child -> child.hopCount(destination, copyWithThis(visitedNodes)) + 1)
                       .min()
                       .orElse(UNREACHABLE);
  }


  public double cost(final Node destination, Set<Node> visitedNodes) {
    if (destination == this) {
      return 0;
    }
    if (visitedNodes.contains(this)) {
      return UNREACHABLE;
    }

    return destinations.entrySet().stream()
                       .mapToDouble(child -> child.getKey().cost(destination, copyWithThis(visitedNodes)) + child.getValue())
                       .min()
                       .orElse(UNREACHABLE);
  }

  private Set<Node> copyWithThis(Set<Node> list) {
    Set<Node> result = new HashSet<>(list);
    result.add(this);
    return result;
  }

  private int valueOrThrowIfUnreachable(final double result) {
    if (result == UNREACHABLE) {
      throw new IllegalArgumentException("Cannot reach destination");
    }
    return (int) result;
  }
}
