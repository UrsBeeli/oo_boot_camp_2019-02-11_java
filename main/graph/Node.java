package graph;

import java.util.HashSet;
import java.util.Set;

import static graph.WeightStrategy.FEWEST_HOPS;
import static graph.WeightStrategy.LEAST_COST;

public class Node {
  private static final Path UNREACHABLE = new InfinitePath();

  private Set<Link> links = new HashSet<>();

  public Node() {
  }

  public void addPathTo(final Node destination, double cost) {
    links.add(new Link(destination, cost));
  }

  public boolean canReach(final Node destination) {
    return path(destination, new HashSet<>(), FEWEST_HOPS) != UNREACHABLE;
  }

  public int hopCount(Node destination) {
    return path(destination, FEWEST_HOPS).hops();
  }

  public double cost(final Node destination) {
    return path(destination, LEAST_COST).cost();
  }

  public Path path(final Node destination) {
    return path(destination, LEAST_COST);
  }

  private Path path(final Node destination, WeightStrategy weightStrategy) {
    final Path path = path(destination, new HashSet<>(), weightStrategy);
    if (path == UNREACHABLE) throw new IllegalArgumentException("No path found");
    return path;
  }

  Path path(final Node destination, Set<Node> visitedNodes, WeightStrategy weightStrategy) {
    if (destination == this) return new ReachablePath();
    if (visitedNodes.contains(this)) return UNREACHABLE;

    return links.stream()
                .map(link -> link.path(destination, copyWithThis(visitedNodes), weightStrategy))
                .min(weightStrategy::compareTo)
                .orElse(UNREACHABLE);
  }

  private Set<Node> copyWithThis(Set<Node> list) {
    Set<Node> result = new HashSet<>(list);
    result.add(this);
    return result;
  }
}
