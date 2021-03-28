package br.com.thiaago.striketutorial.comparator;

import br.com.thiaago.striketutorial.entity.Point;

import java.util.Comparator;

public class PointComparator implements Comparator<Point> {

    @Override
    public int compare(Point o1, Point o2) {
        if (o1.getOrder() > o2.getOrder()) {
            return 1;
        } else if (o1.getOrder() < o2.getOrder()) {
            return -1;
        }
        return 0;
    }
}
