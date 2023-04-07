package photoalbum;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import commands.ChangeColorCommand;
import commands.ChangeHeightCommand;
import commands.ChangeNameCommand;
import commands.ChangeWidthCommand;
import commands.ChangeXRadiusCommand;
import commands.ChangeYRadiusCommand;
import commands.GetHistoryCommand;
import commands.TakeSnapshotCommand;
import exceptions.IllegalShapeTypeException;
import shapes.IShape;
import shapes.ShapeFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PhotoTest {
  IPhoto p;
  IShape s;
  IShape r;
  ShapeFactory rect;
  ShapeFactory ov;


  @Before
  public void setUp() throws IllegalShapeTypeException, NoSuchFieldException, IllegalAccessException {
    p = new Photo();
    s = ShapeFactory.createShape(p, "oval", 1, 2, "blue", "oval", "20 10");
  }

  @Test
  public void testCreateShape() throws IllegalShapeTypeException, NoSuchFieldException, IllegalAccessException {
    IShape oval = ShapeFactory.createShape(p, "oval", 1, 2, "blue", "newShape", "20 10");
    assertEquals("newShape", oval.getName());
    assertEquals(1, oval.getXCoord(), 0.01);
    assertEquals(2, oval.getYCoord(), 0.01);
    assertEquals(Color.BLUE, oval.getColor());
  }

  @Test (expected = IllegalShapeTypeException.class)
  public void testAddNullShapeType() throws IllegalShapeTypeException, NoSuchFieldException, IllegalAccessException {
    IShape o = ShapeFactory.createShape(p, "trapezoid", 1, 2, "blue", "oval", "20 10");
  }

  @Test (expected = IllegalShapeTypeException.class)
  public void testAddNullShapeTypeTwo() throws IllegalShapeTypeException, NoSuchFieldException, IllegalAccessException {
    IShape o = ShapeFactory.createShape(p, "Banana", 1, 2, "blue", "oval", "20 10");
  }

  @Test (expected = IllegalShapeTypeException.class)
  public void testAddEmptyName() throws IllegalShapeTypeException, NoSuchFieldException, IllegalAccessException {
    IShape ov = ShapeFactory.createShape(p, " ", 1, 2, "blue", "noName!!", "20 10");
  }

  @Test
  public void testChangeNameCommand() throws NoSuchFieldException, IllegalAccessException {
    ChangeNameCommand c2 = new ChangeNameCommand(s);
    c2.setName("I am an oval");
    p.executeCommand(c2); // need to set -> then execute
    assertEquals("I am an oval", s.getName());
  }

  @Test
  public void testChangeNameCommandTwo() throws NoSuchFieldException, IllegalAccessException {
    ChangeNameCommand c2 = new ChangeNameCommand(s);
    c2.setName("I am not an oval");
    p.executeCommand(c2);
    assertEquals("I am not an oval", s.getName());
  }
  @Test
  public void testChangeColorCommand() throws NoSuchFieldException, IllegalAccessException {
    ChangeColorCommand c2 = new ChangeColorCommand(s);
    c2.setColor("red");
    p.executeCommand(c2);
    assertEquals(Color.RED, s.getColor());
  }

  @Test
  public void testChangeColorCommandTwo() throws NoSuchFieldException, IllegalAccessException {
    ChangeColorCommand c2 = new ChangeColorCommand(s);
    c2.setColor("green");
    p.executeCommand(c2);
    assertEquals(Color.GREEN, s.getColor());
  }

  @Test
  public void testChangeXCoord() {
    assertEquals(1, s.getXCoord(), 0.01);
    s.setXCoord(5);
    assertEquals(5, s.getXCoord(), 0.01);
  }

  @Test
  public void testChangeYCoord() {
    s.setXCoord(10);
    assertEquals(10, s.getXCoord(), 0.01);
  }

  @Test
  public void testChangeXRadiusCommand() throws IllegalShapeTypeException, NoSuchFieldException, IllegalAccessException {
    IShape oval = ShapeFactory.createShape(p, "oval", 1, 2, "blue", "newShape", "20 10");
    ChangeXRadiusCommand c2 = new ChangeXRadiusCommand(oval);
    c2.setXRadius(50);
    c2.execute();
    assertEquals(50, c2.getXRadius(), 0.01);
  }

  @Test
  public void testChangeYRadiusCommand() throws IllegalShapeTypeException, NoSuchFieldException, IllegalAccessException {
    IShape oval = ShapeFactory.createShape(p, "oval", 1, 2, "blue", "newShape", "20 10");
    ChangeYRadiusCommand c2 = new ChangeYRadiusCommand(oval);
    c2.setRadiusY(120);
    c2.execute();
    assertEquals(120, c2.getRadius(), 0.01);
  }

  @Test
  public void testChangeWidthCommand() throws IllegalShapeTypeException, NoSuchFieldException, IllegalAccessException {
    IShape rect = ShapeFactory.createShape(p, "rectangle", 1, 2, "blue", "newShape", "20 10");
    ChangeWidthCommand c2 = new ChangeWidthCommand(rect);
    c2.setWidth(25);
    c2.execute();
    assertEquals(25, c2.getWidth(), 0.01);
  }

  @Test
  public void testChangeHeightCommand() throws IllegalShapeTypeException, NoSuchFieldException, IllegalAccessException {
    IShape rect = ShapeFactory.createShape(p, "rectangle", 1, 2, "blue", "newShape", "20 10");
    ChangeHeightCommand c2 = new ChangeHeightCommand(rect);
    c2.setHeight(45);
    c2.execute();
    assertEquals(45, c2.getHeight(), 0.01);
  }

  @Test
  public void takeSnapShotCommand() {
    TakeSnapshotCommand snap = new TakeSnapshotCommand(p, "thx u");
    snap.execute();
    assertEquals(1, p.getPhotoAlbum().size());
  }

  @Test
  public void testGetHistoryCommand() {
    TakeSnapshotCommand snap = new TakeSnapshotCommand(p, "thx u");
    snap.execute();
    GetHistoryCommand hist = new GetHistoryCommand(p);
    hist.execute();
    assertEquals(1, p.getHistory().size());
  }

  @Test
  public void testRemoveShape() {
    p.removeShape(s);
    assertEquals(0, p.getPhotoAlbum().size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadHeight() throws IllegalShapeTypeException, NoSuchFieldException, IllegalAccessException {
    IShape rect = ShapeFactory.createShape(p, "rectangle", 1, 2, "blue", "newShape", "20 10");
    ChangeHeightCommand c2 = new ChangeHeightCommand(rect);
    c2.setHeight(-45);
    c2.execute();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadWidth() throws IllegalShapeTypeException, NoSuchFieldException, IllegalAccessException {
    IShape rect = ShapeFactory.createShape(p, "rectangle", 1, 2, "blue", "newShape", "20 10");
    ChangeWidthCommand c2 = new ChangeWidthCommand(rect);
    c2.setWidth(-45);
    c2.execute();
  }


  // test tostring
}

