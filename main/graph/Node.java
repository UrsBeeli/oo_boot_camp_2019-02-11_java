package graph;

import java.util.HashSet;
import java.util.Set;

import static graph.Path.FEWEST_HOPS;
import static graph.Path.LEAST_COST;

public class Node {
  private static final double UNREACHABLE = Double.POSITIVE_INFINITY;

  private Set<Path> paths = new HashSet<>();

  public void addPathTo(final Node destination, double cost) {
    paths.add(new Path(destination, cost));
  }

  public boolean canReach(final Node destination) {
    return weight(destination, new HashSet<>(), FEWEST_HOPS) != UNREACHABLE;
  }

  public int hopCount(Node destination) {
    return (int) weight(destination, FEWEST_HOPS);
  }

  public double cost(final Node destination) {
    return weight(destination, LEAST_COST);
  }

  private double weight(final Node destination, Path.WeightStrategy weightStrategy) {
    double result = weight(destination, new HashSet<>(), weightStrategy);
    if (result == UNREACHABLE) throw new IllegalArgumentException("Cannot reach destination");
    return result;
  }

  double weight(final Node destination, Set<Node> visitedNodes, Path.WeightStrategy weightStrategy) {
    if (destination == this) return 0;
    if (visitedNodes.contains(this)) return UNREACHABLE;

    return paths.stream()
                .mapToDouble(path -> path.weight(destination, copyWithThis(visitedNodes), weightStrategy))
                .min()
                .orElse(UNREACHABLE);
  }

  private Set<Node> copyWithThis(Set<Node> list) {
    Set<Node> result = new HashSet<>(list);
    result.add(this);
    return result;
  }
}
