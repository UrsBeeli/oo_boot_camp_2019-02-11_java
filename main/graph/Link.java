package graph;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Link {
  interface WeightStrategy { double weight(double cost); }

  static final WeightStrategy LEAST_COST = cost -> cost;
  static final WeightStrategy FEWEST_HOPS = ignore -> 1;

  private final Node target;
  private final double cost;

  public Link(Node target, double cost) {
    this.target = target;
    this.cost = cost;
  }

  double weight(Node destination, Set<Node> visitedNodes, WeightStrategy weightStrategy) {
    return target.weight(destination, visitedNodes, weightStrategy) + weightStrategy.weight(cost);
  }

  Path path(Node destination, Set<Node> visitedNodes, Node parent) {
    Path childPath = target.path(destination, visitedNodes);
    if (childPath != null) {
      childPath.addLink(this);
    }
    return childPath;
  }

  static double totalPathLength(final List<Link> links) {
      return links.stream()
                       .mapToDouble(link -> link.cost)
                       .sum();
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;

    final Link otherLink = (Link) other;
    return Double.compare(otherLink.cost, cost) == 0 && Objects.equals(target, otherLink.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(target, cost);
  }

  @Override
  public String toString() {
    return target.toString() + ", "+cost;
  }
}
