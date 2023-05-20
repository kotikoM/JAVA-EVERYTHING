package com.epam.rd.autotasks.segments;


class Segment {
    Point start;
    Point end;

    public Segment(Point start, Point end) {
        //check if start and end are same points if not continue
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            throw new RuntimeException();
        }
        else {
            this.start=start;
            this.end=end;
        }
    }

    double length() {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) +
                Math.pow(end.getY() - start.getY(), 2));
    }

    Point middle() {
        return new Point(((start.getX() + end.getX()) / 2),
                (start.getY() + end.getY()) / 2);
    }

    Point intersection(Segment another) {
        //finding intersection point
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = another.start.getX();
        double y3 = another.start.getY();
        double x4 = another.end.getX();
        double y4 = another.end.getY();


        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        // Check if lines are parallel
        if (denominator == 0) {
            return null;
        }

        // Calculate the intersection point
        double intersectionX = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator;
        double intersectionY = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator;

        // Check if the intersection point is within both segments
        if (!isPointOnSegment(intersectionX, intersectionY) || !another.isPointOnSegment(intersectionX, intersectionY)) {
            return null;
        }



        return new Point(intersectionX, intersectionY);
    }


    private boolean isPointOnSegment(double x, double y) {
        double minX = Math.min(start.getX(), end.getX());
        double maxX = Math.max(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        double maxY = Math.max(start.getY(), end.getY());

        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }



}

