package com.epam.rd.autotasks.figures;

class Triangle extends Figure {
    Point a;
    Point b;
    Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        double sum1 = (this.a.getX() * this.b.getY()) + (this.b.getX() * this.c.getY()) +
                (this.c.getX() * this.a.getY());

        double sum2 = (this.b.getX() * this.a.getY()) + (this.c.getX() * this.b.getY()) +
                (this.a.getX() * this.c.getY());


        return 0.5 * Math.abs(sum1 - sum2);
    }

    @Override
    public String pointsToString() {
        return String.format("(%.1f,%.1f)(%.1f,%.1f)(%.1f,%.1f)", a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY());
    }

    public String toString(){
        return "Triangle["+this.pointsToString()+"]";
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

        return leftmostPoint;
    }
}
