package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static graph.Link.FEWEST_HOPS;
import static graph.Link.LEAST_COST;

public class Node {
  private static final double UNREACHABLE = Double.POSITIVE_INFINITY;

  private final String label;
  private Set<Link> links = new HashSet<>();

  public Node(final String label) {
    this.label = label;
  }

  public void addPathTo(final Node destination, double cost) {
    links.add(new Link(destination, cost));
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

  private double weight(final Node destination, Link.WeightStrategy weightStrategy) {
    double result = weight(destination, new HashSet<>(), weightStrategy);
    if (result == UNREACHABLE) throw new IllegalArgumentException("Cannot reach destination");
    return result;
  }

  double weight(final Node destination, Set<Node> visitedNodes, Link.WeightStrategy weightStrategy) {
    if (destination == this) return 0;
    if (visitedNodes.contains(this)) return UNREACHABLE;

    return links.stream()
                .mapToDouble(link -> link.weight(destination, copyWithThis(visitedNodes), weightStrategy))
                .min()
                .orElse(UNREACHABLE);
  }

  private Set<Node> copyWithThis(Set<Node> list) {
    Set<Node> result = new HashSet<>(list);
    result.add(this);
    return result;
  }

  public Path path(final Node destination) {
    final Path path = path(destination, new HashSet<>());
    if (path == null) throw new IllegalArgumentException("No path found");
    return path;
  }

  Path path(final Node destination, Set<Node> visitedNodes) {
    if (destination == this) return new Path();
    if (visitedNodes.contains(this)) return null;

    return links.stream()
                .map(link -> link.path(destination, copyWithThis(visitedNodes), this))
                .filter(Objects::nonNull)
                .min(Path::compareTo)
                .orElse(null);

  }

  @Override
  public String toString() {
    return "Node ["+label+"]";
  }
}
