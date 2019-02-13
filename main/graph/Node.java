package graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class Node {
  private Set<Node> destinations = new HashSet<>();

  public Node addPathTo(final Node destination) {
    destinations.add(destination);
    return destination;
  }

  public boolean canReach(final Node destination) {
    return hopCount(destination) != null;
  }

  public Integer hopCount(Node destination) {
    return hopCount(destination, new HashMap<>());
  }

  private <T> Integer hopCount(Node destination, Map<Node, Integer> visitedNodes) {
    if (this == destination) {
      return 0;
    }
    if (visitedNodes.containsKey(this)) {
      return visitedNodes.get(this);
    }
    visitedNodes.put(this, null);

    return destinations.stream()
                       .map(d -> d.hopCount(destination, visitedNodes))
                       .filter(Objects::nonNull)
                       .min(Integer::compareTo)
                       .map(hop -> {
                         hop = hop + 1;
                         visitedNodes.put(this, hop);
                         return hop;
                       })
                       .orElse(null);
  }
}
