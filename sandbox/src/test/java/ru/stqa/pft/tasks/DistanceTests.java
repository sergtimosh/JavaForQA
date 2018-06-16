package ru.stqa.pft.tasks;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.stqa.pft.tasks.DistanceBtwPoints.distance;

public class DistanceTests {

// проверка метода distance из класса Point
  @Test
  public void testDistance() {

    Point p1 = new Point(-10, 20);
    Point p2 = new Point(15, -35);
    assert p1.distance(p2) == 60.41522986797286;

  }

  @Test
  public void testDistance2() {

    Point p1 = new Point(112.8, -2.5);
    Point p2 = new Point(15.3, 24.6);
    assert p1.distance(p2) == 101.19614617167987;
    Assert.assertEquals(p1.distance(p2), 101.19614617167987);

  }

//проверка статической функции distance() из класса DistanceBtwPoints

  @Test
  public static void testDistance3() {

    Point p1 = new Point(23, 24);
    Point p2 = new Point(35, 21.7);
    Assert.assertEquals(distance(p1, p2), 12.218428704215611);
  }
}