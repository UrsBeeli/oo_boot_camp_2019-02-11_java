package finder;

import java.util.Collection;

public interface Comparable<T> {
  boolean isLargerThan(T other);

  public static <T extends Comparable<T>> T findLargest(final Collection<T> collection) {
    T largest = null;
    for (T t : collection) {
      if (largest == null || t.isLargerThan(largest)) {
        largest = t;
      }
    }
    return largest;
  }
}
