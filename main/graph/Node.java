package graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static graph.Path.COST_COMPARATOR;
import static graph.Path.HOP_COUNT_COMPARATOR;
import static java.util.stream.Collectors.toList;

public class Node {

  private Set<Link> links = new HashSet<>();

  public void addPathTo(final Node destination, double cost) {
    links.add(new Link(destination, cost));
  }

  public boolean canReach(final Node destination) {
    return paths(destination, new HashSet<>()).count() > 0;
  }

  public int hopCount(Node destination) {
    return path(destination, HOP_COUNT_COMPARATOR).hopCount();
  }

  public double cost(final Node destination) {
    return path(destination, COST_COMPARATOR).cost();
  }

  public Path path(final Node destination) {
    return path(destination, COST_COMPARATOR);
  }

  public List<Path> paths(final Node destination) {
    return paths(destination, new HashSet<>()).collect(toList());
  }

  private Path path(final Node destination, Comparator<Path> pathComparator) {
    return paths(destination, new HashSet<>())
        .min(pathComparator)
        .orElseThrow(() -> new IllegalArgumentException("No path found"));
  }

  Stream<Path> paths(final Node destination, Set<Node> visitedNodes) {
    if (destination == this) return Stream.of(new Path());
    if (visitedNodes.contains(this)) return Stream.empty();

    return links.stream().flatMap(link -> link.paths(destination, copyWithThis(visitedNodes)));
  }

  private Set<Node> copyWithThis(Set<Node> list) {
    Set<Node> result = new HashSet<>(list);
    result.add(this);
    return result;
  }
}
