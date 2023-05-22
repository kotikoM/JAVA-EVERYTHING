package com.epam.rd.autotasks.triangle;

class Triangle {

    Point a;
    Point b;
    Point c;


    public Triangle(Point a, Point b, Point c) {
        if (a.getX()==b.getX() && a.getY()==b.getY()){
            throw new RuntimeException();
        }
        else if (a.getX()==c.getX() && a.getY()==c.getY()){
            throw new RuntimeException();
        }
        else if (c.getX()==b.getX() && c.getY()==b.getY()){
            throw new RuntimeException();
        }
        else if (a.getX()==b.getX() && b.getX()==c.getX()){
            throw new RuntimeException();
        }
        //if points are collinear
        else if (((c.getY()-b.getY())/(c.getX()-b.getX()))==
                ((a.getY()-b.getY())/(a.getX()-b.getX()))){
            throw new RuntimeException();
        }
        else {
            this.a=a;
            this.b=b;
            this.c=c;
        }
    }

    public double area() {
        double sum1 = (this.a.getX() * this.b.getY()) + (this.b.getX() * this.c.getY()) +
                (this.c.getX() * this.a.getY());

        double sum2 = (this.b.getX() * this.a.getY()) + (this.c.getX() * this.b.getY()) +
                (this.a.getX() * this.c.getY());


        return 0.5 * Math.abs(sum1 - sum2);
    }

    public Point centroid(){
        double centroidX = (this.a.getX() + this.b.getX() + this.c.getX()) / 3;
        double centroidY = (this.a.getY() + this.b.getY() + this.c.getY()) / 3;
        return new Point(centroidX, centroidY);
    }

}
