package graph;

import java.util.HashSet;
import java.util.Set;

public class Node {
  private Set<Node> destinations = new HashSet<>();

  public void addPathTo(final Node destination) {
    destinations.add(destination);
  }

  public boolean canReach(final Node destination) {
    return canReach(destination, new HashSet<>());
  }

  private boolean canReach(final Node destination, final Set<Node> alreadyVisited) {
    if (destination == this) {
      return true;
    }
    if (alreadyVisited.contains(this)) {
      return false;
    }

    alreadyVisited.add(this);

    return destinations.stream().anyMatch(d -> d.canReach(destination, alreadyVisited));
  }
}
