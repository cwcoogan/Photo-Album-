package photoalbum;

public class ChangeColorCommand implements Command{
  private final IShape shape;

  public ChangeColorCommand(IShape shape) {
    this.shape = shape;
  }

  @Override
  public void execute(String Color) {
    shape.setColor(Double.parseDouble(Color));
  }
}