package ru.stqa.pft.tasks;

public class DistanceBtwPoints {

  public static void main(String[] args){

    Point p1 = new Point(-10, 20);
    Point p2 = new Point(15, -35);
    System.out.println("Distance between points(using function) " + "(x=" + p1.x + ", " + "y=" + p1.y + ")" + " and " + "(x=" + p2.x + ", " + "y=" + p2.y + ")" +" is " + distance(p1, p2));
    System.out.println("Distance between points(using method) " + "(x=" + p1.x + ", " + "y=" + p1.y + ")" + " and " + "(x=" + p2.x + ", " + "y=" + p2.y + ")" +" is " + p1.distance(p2));

  }

  public static double distance(Point p1, Point p2){
    return Math.hypot(p1.x-p2.x, p1.y-p2.y);
  }
}
