package commands;

import java.awt.*;
import java.util.Map;

import Model.IPhoto;
import Model.Photo;
import Model.Snapshot;
import View.IView;
import shapes.IShape;

/**
 * The type Change color command.
 */
public class ChangeColorCommand implements Command {
  private final IShape shape;
  private Color color;

  /**
   * Instantiates a new Change color command.
   *
   * @param shape the shape
   */
  public ChangeColorCommand(IShape shape, Color color) {
    this.shape = shape;
    this.color = color;
  }

  @Override
  public void execute() throws NoSuchFieldException, IllegalAccessException {
    shape.setColor(color);
  }
}
