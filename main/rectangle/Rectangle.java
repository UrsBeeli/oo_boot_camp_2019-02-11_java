package rectangle;

public class Rectangle {

  private final double height;
  private final double width;

  public Rectangle(final double height, final double width) {
    this.height = height;
    this.width = width;
  }

  public double area() {
    return height * width;
  }
}
