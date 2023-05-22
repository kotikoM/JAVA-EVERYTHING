package com.epam.rd.autotasks.figures;

class Circle extends Figure {
    Point center;
    double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }


    @Override
    public double area() {
        return Math.PI * (Math.pow(radius, 2));
    }

    @Override
    public String pointsToString() {
        return String.format("(%.1f,%.1f)", center.getX(), center.getY());
    }

    public String toString(){
        return "Circle["+this.pointsToString()+radius+"]";
    }

    @Override
    public Point leftmostPoint() {
        return new Point (this.center.getX()-radius, this.center.getY()) ;
    }
}
