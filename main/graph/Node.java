package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static graph.Path.COST_COMPARATOR;
import static graph.Path.HOP_COUNT_COMPARATOR;
import static java.util.stream.Collectors.toList;

public class Node {
  private static final Path UNREACHABLE = new InfinitePath();

  private Set<Link> links = new HashSet<>();

  public Node() {
  }

  public void addPathTo(final Node destination, double cost) {
    links.add(new Link(destination, cost));
  }

  public boolean canReach(final Node destination) {
    return path(destination, new HashSet<>(), HOP_COUNT_COMPARATOR) != UNREACHABLE;
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

  private Path path(final Node destination, Comparator<Path> pathComparator) {
    final Path path = path(destination, new HashSet<>(), pathComparator);
    if (path == UNREACHABLE) {
      throw new IllegalArgumentException("No path found");
    }
    return path;
  }

  Path path(final Node destination, Set<Node> visitedNodes, Comparator<Path> pathComparator) {
    if (destination == this) {
      return new ReachablePath();
    }
    if (visitedNodes.contains(this)) {
      return UNREACHABLE;
    }

    return links.stream()
                .map(link -> link.path(destination, copyWithThis(visitedNodes), pathComparator))
                .min(pathComparator)
                .orElse(UNREACHABLE);
  }

  public List<Path> paths(final Node destination) {
    return paths(destination, new HashSet<>());
  }

  List<Path> paths(final Node destination, Set<Node> visitedNodes) {
    if (destination == this) {
      return newListWithReachablePath();
    }
    if (visitedNodes.contains(this)) {
      return new ArrayList<>();
    }

    return links.stream()
                .map(link -> link.paths(destination, copyWithThis(visitedNodes)))
                .flatMap(Collection::stream)
                .collect(toList());
  }

  private List<Path> newListWithReachablePath() {
    List<Path> result = new ArrayList<>();
    result.add(new ReachablePath());
    return result;
  }


  private Set<Node> copyWithThis(Set<Node> list) {
    Set<Node> result = new HashSet<>(list);
    result.add(this);
    return result;
  }
}
