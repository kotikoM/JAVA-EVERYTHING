package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure {
    Point a;
    Point b;
    Point c;
    Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double area() {
        //shoelace formula works well in this case
        double sum1 = (this.a.getX() * this.b.getY()) + (this.b.getX() * this.c.getY()) +
                (this.c.getX() * this.d.getY()) + (this.d.getX() * this.a.getY());

        double sum2 = (this.b.getX() * this.a.getY()) + (this.c.getX() * this.b.getY()) +
                (this.d.getX() * this.c.getY()) + (this.a.getX() * this.d.getY());

        return 0.5 * Math.abs(sum1 - sum2);

    }

    @Override
    public String pointsToString() {
        return String.format("(%.1f,%.1f)(%.1f,%.1f)(%.1f,%.1f)(%.1f,%.1f)",
                a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY(), d.getX(), d.getY());
    }


    public String toString(){
        return "Quadrilateral["+this.pointsToString()+"]";
    }

    @Override
    public Point leftmostPoint() {
        //temporarily take a as leftmost point
        Point leftmostPoint = this.a;

        if (this.b.getX() < leftmostPoint.getX()) {
            leftmostPoint = this.b;
        }
        if (this.c.getX() < leftmostPoint.getX()) {
            leftmostPoint = this.c;
        }
        if (this.d.getX() < leftmostPoint.getX()) {
            leftmostPoint = this.d;
        }

        return leftmostPoint;
    }
}
