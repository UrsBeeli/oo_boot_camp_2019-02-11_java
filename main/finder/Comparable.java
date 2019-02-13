package finder;

import java.util.Collection;

public interface Comparable<T> {
  boolean isBetterThan(T other);

  public static <S extends Comparable<S>> S findBest(final Collection<S> collection) {
    S champion = null;
    for (S challenger : collection) {
      if (champion == null || challenger.isBetterThan(champion)) {
        champion = challenger;
      }
    }
    return champion;
  }
}
