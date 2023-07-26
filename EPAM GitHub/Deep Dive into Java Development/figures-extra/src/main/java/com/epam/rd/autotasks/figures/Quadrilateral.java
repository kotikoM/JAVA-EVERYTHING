package com.epam.rd.autotasks.figures;

import static java.lang.Math.sqrt;

class Quadrilateral extends Figure {
    Point a;
    Point b;
    Point c;
    Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (a!=null && b!=null && c!=null && d!=null){
            Point center= new Point((a.getX() + b.getX() + c.getX() + d.getX()) / 4,
                    (a.getY() + b.getY() + c.getY() + d.getY()) / 4);


            new Triangle(a, b, c);
            new Triangle(a, d, b);
            new Triangle(c, a, d);
            new Triangle(c, b, d);


            new Triangle(a, b, center);
            new Triangle(a, d, center);
            new Triangle(c, d, center);
            new Triangle(c, b, center);

            double crossProductAB = crossProduct(a, b, c);
            double crossProductBC = crossProduct(b, c, d);
            double crossProductCD = crossProduct(c, d, a);
            double crossProductDA = crossProduct(d, a, b);

            if (crossProductAB * crossProductBC < 0 || crossProductBC * crossProductCD < 0 ||
                    crossProductCD * crossProductDA < 0 || crossProductDA * crossProductAB < 0) {
                throw new IllegalArgumentException("The quadrilateral is concave.");
            }

            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
        else
            throw new IllegalArgumentException();
    }

    public static double crossProduct(Point p1, Point p2, Point p3) {
        return (p2.getX() - p1.getX()) * (p3.getY() - p2.getY()) - (p2.getY() - p1.getY()) * (p3.getX() - p2.getX());
    }
    @Override
    public Point centroid() {
        double totalArea = calculateArea(this.a, this.b, this.c) + calculateArea(this.a, this.c, this.d);
        double centerX = (calculateCentroidCoordinate(this.a.getX(), this.b.getX(), this.c.getX()) * calculateArea(this.a, this.b, this.c)
                + calculateCentroidCoordinate(this.a.getX(), this.c.getX(), this.d.getX()) * calculateArea(this.a, this.c, this.d)) / totalArea;
        double centerY = (calculateCentroidCoordinate(this.a.getY(), this.b.getY(), this.c.getY()) * calculateArea(this.a, this.b, this.c)
                + calculateCentroidCoordinate(this.a.getY(), this.c.getY(), this.d.getY()) * calculateArea(this.a, this.c, this.d)) / totalArea;
        return new Point(centerX, centerY);
    }

    private static double calculateArea(Point p1, Point p2, Point p3) {
        return Math.abs((p1.getX() * (p2.getY() - p3.getY()) + p2.getX() * (p3.getY() - p1.getY()) + p3.getX() * (p1.getY() - p2.getY())) / 2);
    }

    private static double calculateCentroidCoordinate(double x1, double x2, double x3) {
        return (x1 + x2 + x3) / 3;
    }


    @Override
    public boolean isTheSame(Figure figure) {

        if (figure instanceof Quadrilateral){
            boolean acc= true;
            Point[] points1 = {this.a, this.b, this.c, this.d};

            Point[] points2 = {((Quadrilateral) figure).a, ((Quadrilateral) figure).b, ((Quadrilateral) figure).c, ((Quadrilateral) figure).d};


            // Check if all points of quad1 are present in quad2
            for (Point p1 : points1) {
                boolean found = false;

                for (Point p2 : points2) {
                    if ((p1.getX()== p2.getX() && p1.getY()==p2.getY()) |
                            //line below if not necessary but due to rooting and squaring inaccuracy
                            //tester checks if sqrt(2) * sqrt(2) is same as 2 but in reality it is 2 but in java it is 2.000000000000000004
                            (Math.abs(p1.getX()-p2.getX())<0.0001 && Math.abs((p1.getY()-p2.getY()))<0.0001)) {
                        found = true;
                        break;
                    }
                }
                acc=acc&&found;
            }

            return acc;
        }
        return false;
    }

    public double area() {
        //shoelace formula works well in this case
        double sum1 = (this.a.getX() * this.b.getY()) + (this.b.getX() * this.c.getY()) +
                (this.c.getX() * this.d.getY()) + (this.d.getX() * this.a.getY());

        double sum2 = (this.b.getX() * this.a.getY()) + (this.c.getX() * this.b.getY()) +
                (this.d.getX() * this.c.getY()) + (this.a.getX() * this.d.getY());

        return 0.5 * Math.abs(sum1 - sum2);

    }

    public static void main(String[] args) {
        Point a = new Point(0, 0);
        Point b = new Point(1, 10);
        Point c = new Point(11, 11);
        Point d = new Point(19, 2);

        Quadrilateral quadrilateral=new Quadrilateral(a, b, c, d);
        Quadrilateral quadrilateral11= new Quadrilateral(a, b, c, new Point(d.getX(), sqrt(2)*sqrt(2)));

        System.out.println(d.isSame(new Point(d.getX(), sqrt(2)*sqrt(2))));
        System.out.println(quadrilateral11.isTheSame(quadrilateral));
    }

}

