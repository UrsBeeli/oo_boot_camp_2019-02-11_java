package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static graph.Link.FEWEST_HOPS;
import static graph.Link.LEAST_COST;

public class Node {
  private final String label;
  private Set<Link> links = new HashSet<>();

  public Node(final String label) {
    this.label = label;
  }

  public void addPathTo(final Node destination, double cost) {
    links.add(new Link(destination, cost));
  }

  public boolean canReach(final Node destination) {
    return path(destination, new HashSet<>(), FEWEST_HOPS) != null;
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
    final Path path = path(destination, new HashSet<>(), LEAST_COST);
    if (path == null) throw new IllegalArgumentException("No path found");
    return path;
  }

  private Path path(final Node destination, Link.WeightStrategy weightStrategy) {
    final Path path = path(destination, new HashSet<>(), weightStrategy);
    if (path == null) throw new IllegalArgumentException("No path found");
    return path;
  }

  Path path(final Node destination, Set<Node> visitedNodes, Link.WeightStrategy weightStrategy) {
    if (destination == this) return new Path(weightStrategy);
    if (visitedNodes.contains(this)) return null;

    return links.stream()
                .map(link -> link.path(destination, copyWithThis(visitedNodes), weightStrategy))
                .filter(Objects::nonNull)
                .min(Path::compareTo)
                .orElse(null);
  }

  @Override
  public String toString() {
    return "Node ["+label+"]";
  }
}
