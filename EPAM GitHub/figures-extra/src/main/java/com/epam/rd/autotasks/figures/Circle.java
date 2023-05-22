package com.epam.rd.autotasks.figures;

class Circle extends Figure {
    Point center;
    double radius;

    public Circle(Point center, double radius) {
        if (center!=null) {
            if (radius > 0) {
                this.center = center;
                this.radius = radius;
            } else
                throw new IllegalArgumentException();
        }
        else
            throw new IllegalArgumentException();
    }


    @Override
    public Point centroid() {
        return this.center;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure instanceof Circle) {
            return (this.center.getX()==((Circle) figure).center.getX() | (Math.abs(this.center.getX()-((Circle) figure).center.getX()) <0.0001) ) &&
                    (this.center.getY()==((Circle) figure).center.getY() | (Math.abs(this.center.getY()-((Circle) figure).center.getY()) <0.0001) ) &&
                    (this.radius==((Circle) figure).radius | (Math.abs(this.radius-((Circle) figure).radius) <0.0001));
        }
        return false;
    }

}

