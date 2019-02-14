package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Node {
  private static final double UNREACHABLE = Double.POSITIVE_INFINITY;

  private Map<Node, Integer> destinations = new HashMap<>();

  public void addPathTo(final Node destination, int cost) {
    destinations.put(destination, cost);
  }

  public boolean canReach(final Node destination) {
    return cost(destination, new HashSet<>(), Node::hops) != UNREACHABLE;
  }

  public int hopCount(Node destination) {
    return valueOrThrowIfUnreachable(cost(destination, new HashSet<>(), Node::hops));
  }

  public int cost(final Node destination) {
    return valueOrThrowIfUnreachable(cost(destination, new HashSet<>(), Map.Entry::getValue));
  }

  private double cost(final Node destination, Set<Node> visitedNodes, Function<Map.Entry<Node, Integer>, Integer> pathWeight) {
    if (destination == this) {
      return 0;
    }
    if (visitedNodes.contains(this)) {
      return UNREACHABLE;
    }

    return destinations.entrySet().stream()
                       .mapToDouble(child -> child.getKey().cost(destination, copyWithThis(visitedNodes), pathWeight) + pathWeight.apply(child))
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

  private static Integer hops(final Map.Entry<Node, Integer> nodeIntegerEntry) {
    return 1;
  }

}
