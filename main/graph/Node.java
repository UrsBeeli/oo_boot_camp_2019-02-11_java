package graph;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class Node {
  private static final double UNREACHABLE = Double.POSITIVE_INFINITY;

  private Set<Path> paths = new HashSet<>();

  public void addPathTo(final Node destination, int cost) {
    paths.add(new Path(destination, cost));
  }

  public boolean canReach(final Node destination) {
    return weight(destination, new HashSet<>(), Path::hops) != UNREACHABLE;
  }

  public int hopCount(Node destination) {
    return valueOrThrowIfUnreachable(weight(destination, new HashSet<>(), Path::hops));
  }

  public int cost(final Node destination) {
    return valueOrThrowIfUnreachable(weight(destination, new HashSet<>(), Path::weight));
  }

  double weight(final Node destination, Set<Node> visitedNodes, Function<Path, Integer> pathWeight) {
    if (destination == this) {
      return 0;
    }
    if (visitedNodes.contains(this)) {
      return UNREACHABLE;
    }

    return paths.stream()
                .mapToDouble(path -> path.weight(destination, copyWithThis(visitedNodes), pathWeight))
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
