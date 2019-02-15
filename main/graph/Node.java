package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static graph.Path.FEWEST_HOPS;
import static graph.Path.LEAST_COST;

public class Node {
  private static final UnreachablePath UNREACHABLE = new UnreachablePath();
  private final String label;
  private Set<Link> links = new HashSet<>();

  public Node(final String label) {
    this.label = label;
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

  private Set<Node> copyWithThis(Set<Node> list) {
    Set<Node> result = new HashSet<>(list);
    result.add(this);
    return result;
  }

  public Path path(final Node destination) {
    return path(destination, new HashSet<>(), LEAST_COST);
  }

  private Path path(final Node destination, Path.WeightStrategy weightStrategy) {
    final Path path = path(destination, new HashSet<>(), weightStrategy);
    if (path == UNREACHABLE) throw new IllegalArgumentException("No path found");
    return path;
  }

  Path path(final Node destination, Set<Node> visitedNodes, Path.WeightStrategy weightStrategy) {
    if (destination == this) return new Path(weightStrategy);
    if (visitedNodes.contains(this)) return UNREACHABLE;

    return links.stream()
                .map(link -> link.path(destination, copyWithThis(visitedNodes), weightStrategy))
                .min(Path::compareTo)
                .orElse(UNREACHABLE);
  }

  @Override
  public String toString() {
    return "Node ["+label+"]";
  }
}
