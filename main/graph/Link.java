package graph;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Link {
  interface WeightStrategy { double weight(List<Link> link); }

  static final WeightStrategy LEAST_COST = links -> totalPathLength(links);
  static final WeightStrategy FEWEST_HOPS = links -> links.size();

  private final Node target;
  private final double cost;

  public Link(Node target, double cost) {
    this.target = target;
    this.cost = cost;
  }

  Path path(Node destination, Set<Node> visitedNodes, WeightStrategy weightStrategy) {
    Path result = target.path(destination, visitedNodes, weightStrategy);
    if (result != null) {
      result.prepend(this);
    }
    return result;
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
